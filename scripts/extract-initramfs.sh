#!/bin/bash

#
# extract-initramfs:
#
# Extracts and decompresses kernel binary (vmlinux) from a bzImage then searches
# the extracted kernel for an initramfs cpio archive.
#
# Assumes kernel is compressed with LZ4 and that embedded cpio archive is not compressed.
#

usage() {
cat <<-EOF
USAGE: extract-initramfs [options] [kernel bzImage]

OPTIONS

    -d <directory-name>     Choose a non-default directory for extracted initramfs (default ${PWD}/initramfs)

EOF
exit 0
}

info() {
    printf "[+] ${1}\n"
}

error() {
    >&2 echo "[!] ${1}"
    exit 1
}

REQUIRED_UTILS="cpio lz4 readelf"

confirm_required_utils() {
    local required=${1}
    local missing=""

    for util in ${required}; do
        which ${util} > /dev/null || missing="${missing}${util} "
    done

    if [[ -n ${missing} ]]; then
        error "Required utilities are not installed: ${missing}"
    fi
}

confirm_required_utils "${REQUIRED_UTILS}"

CPIO_HEADER='070701'
LZ4_HEADER='\x02!L\x18'

img=""
workdir=$(mktemp -d /tmp/initramfs-extract.XXXX)
vmlinux=${workdir}/vmlinux
outdir=${PWD}/initramfs
trap "rm -rf $workdir" 0

#
# Search for $pattern in $infile and output a list of offsets where the pattern is found
#

binary_grep() {
    local pattern=$1 infile=$2
    grep --only-matching --byte-offset --text --perl-regexp ${pattern} ${infile} | cut -d: -f1
}

#
# Output $infile starting at byte position $offset
#

binary_tail() {
    local offset=$1 infile=$2
    tail -c +$(($offset + 1)) ${infile}
}

check_elf() {
    readelf -h ${1} > /dev/null 2>&1
}

#
# Search for $pattern in $img file and for each offset found, attempt to decompress 
# the file starting at that position by piping through $cmdline
#

try_decompress() {
    local pattern=$1 cmdline=$2
    for pos in $(binary_grep ${pattern} ${img}); do
        printf "Testing offset %08x\n" $pos
        binary_tail $pos $img | ${cmdline} > ${vmlinux} 2> /dev/null
        check_elf ${vmlinux} && return 0
    done
    return 1
}


#
# Add more patterns and command lines from extract-vmlinux script in linux kernel source tree
# to support more compression formats
#

decompress_kernel() {
    try_decompress $LZ4_HEADER 'lz4 -d' && return 0
    error "Failed to decompress kernel"
}

#
#  CPIO format is a list of file entries where each entry begins with a header that contains only
#  ASCII hexadecimal digits. To avoid false positives matching only the magic value, check to make
#  sure that some of the the bytes following the magic field are ASCII hex digits.
#
#  From https://www.systutorials.com/docs/linux/man/5-cpio/
#
#  New ASCII Format
#
#  The "new" ASCII format uses 8-byte hexadecimal fields for all numbers and separates device numbers 
#  into separate fields for major and minor numbers.
#
#    struct cpio_newc_header {
#            char    c_magic[6];
#            char    c_ino[8];
#            char    c_mode[8];
#            char    c_uid[8];
#            char    c_gid[8];
#            char    c_nlink[8];
#            char    c_mtime[8];
#            char    c_filesize[8];
#            char    c_devmajor[8];
#            char    c_devminor[8];
#            char    c_rdevmajor[8];
#            char    c_rdevminor[8];
#            char    c_namesize[8];
#            char    c_check[8];
#    };
#

confirm_cpio() {
    # Check that there are 100 ascii hex digit characters at offset
    local offset=${1} checklen=100
    dd if=${vmlinux} bs=1 count=${checklen} skip=${offset} 2> /dev/null | grep -q "[[:xdigit:]]\{${checklen}\}"
}

extract_cpio() {
    cpio --make-directories --extract --no-absolute-filenames -D ${outdir} 2> /dev/null
}

extract_initramfs() {
    for pos in $(binary_grep ${CPIO_HEADER} ${vmlinux}); do
        printf "Found CPIO: %08x\n" $pos
        if confirm_cpio ${pos}; then
            mkdir ${outdir}
            binary_tail $pos ${vmlinux} | extract_cpio
            info "Initramfs files extracted to ${outdir}"
            exit 0
        fi
    done
}

while [[ $# -gt 0 ]]; do
    key=${1}
    case $key in
        -d)
            outdir="$(realpath ${2})"
            shift
            shift
            ;;
        -h|--help)
            usage
            ;;
        -*)
            printf "Unknown option ${key}\n"
            usage
            ;;
        *)
            img=${1}
            shift
            ;;
    esac
done

[[ -z ${img} ]] && usage
[[ -f ${img} ]] || error "kernel image file ${img} does not exist"

if [[ -d ${outdir} ]]; then
    error "Output directory ${outdir} already exists. Remove it or choose a different directory with -d option."
fi

decompress_kernel
extract_initramfs

