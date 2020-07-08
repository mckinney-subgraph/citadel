SUMMARY = "SpiderMonkey is Mozilla's JavaScript engine written in C/C++"
HOMEPAGE = "https://developer.mozilla.org/en-US/docs/Mozilla/Projects/SpiderMonkey"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dc9b6ecd19a14a54a628edaaf23733bf"

SRC_URI = "http://ftp.debian.org/debian/pool/main/m/mozjs60/mozjs60_60.2.3.orig.tar.xz \
           file://0001-do-not-create-python-environment-new.patch \
           file://0002-do-not-include-requireddefines.patch \
           file://0003-fix-coredump-caused-by-getenv.patch \
           file://0004-new-fix-cannot-find-link.patch \
           file://0005-do-not-use-autoconf-213-to-refresh-old-configure.patch \
           "
SRC_URI[md5sum] = "ef7c243c2ef474c7b055c0b7f91ea2f2"
SRC_URI[sha256sum] = "4163add2c4c25499fb45d7cca7cab9ecbede0cb054b9bcfc9abaee84733c4d04"

inherit autotools pkgconfig perlnative pythonnative

inherit distro_features_check
CONFLICT_DISTRO_FEATURES_mipsarchn32 = "ld-is-gold"

DEPENDS += "nspr zlib"

# Disable null pointer optimization in gcc >= 6
# https://bugzilla.redhat.com/show_bug.cgi?id=1328045
CFLAGS += "-fno-tree-vrp -fno-strict-aliasing -fno-delete-null-pointer-checks"
CXXFLAGS += "-fno-tree-vrp -fno-strict-aliasing -fno-delete-null-pointer-checks"

# nspr's package-config is ignored so set libs manually
EXTRA_OECONF = " \
    --target=${TARGET_SYS} \
    --host=${BUILD_SYS} \
    --prefix=${prefix} \
    --libdir=${libdir} \
    --disable-tests \
    --disable-jemalloc \
    --with-nspr-libs='-lplds4 -lplc4 -lnspr4' \
    ${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', "--enable-gold", '--disable-gold', d)} \
"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)}"
PACKAGECONFIG[x11] = "--x-includes=${STAGING_INCDIR} --x-libraries=${STAGING_LIBDIR},--x-includes=no --x-libraries=no,virtual/libx11"

EXTRA_OEMAKE_task-compile += "OS_LDFLAGS='-Wl,-latomic ${LDFLAGS}'"
EXTRA_OEMAKE_task-install += "STATIC_LIBRARY_NAME=js_static"

do_configure() {
    export SHELL="/bin/sh"
    export TMP="${B}"
    ${S}/js/src/configure ${EXTRA_OECONF}
}

do_compile_prepend() {
    export SHELL="/bin/sh"
    export S
    export PYTHONPATH
    cd ${S}
    for sub_dir in python testing/mozbase third_party/python; do
        for module_dir in `ls $sub_dir -1`;do
            [ $module_dir = "virtualenv" ] && continue
            if [ -d "${S}/$sub_dir/$module_dir" ];then
                PYTHONPATH="$PYTHONPATH:${S}/$sub_dir/$module_dir"
            fi
        done
    done
    PYTHONPATH="$PYTHONPATH:${S}/config:${S}/build"
    cd -
}

do_install_prepend() {
    export SHELL="/bin/sh"
    export S
    export PYTHONPATH
    cd ${S}
    for sub_dir in python testing/mozbase third_party/python; do
        for module_dir in `ls $sub_dir -1`;do
            [ $module_dir = "virtualenv" ] && continue
            if [ -d "${S}/$sub_dir/$module_dir" ];then
                PYTHONPATH="$PYTHONPATH:${S}/$sub_dir/$module_dir"
            fi
        done
    done
    PYTHONPATH="$PYTHONPATH:${S}/config:${S}/build"
    cd -
}

PACKAGES =+ "lib${BPN}"
FILES_lib${BPN} += "${libdir}/lib*.so"
FILES_${PN}-dev += "${bindir}/js52-config"
RPROVIDES_lib${BPN} += "libmozjs-52"

# Fails to build with thumb-1 (qemuarm)
#| {standard input}: Assembler messages:
#| {standard input}:2172: Error: shifts in CMP/MOV instructions are only supported in unified syntax -- `mov r2,r1,LSR#20'
#| {standard input}:2173: Error: unshifted register required -- `bic r2,r2,#(1<<11)'
#| {standard input}:2174: Error: unshifted register required -- `orr r1,r1,#(1<<20)'
#| {standard input}:2176: Error: instruction not supported in Thumb16 mode -- `subs r2,r2,#0x300'
#| {standard input}:2178: Error: instruction not supported in Thumb16 mode -- `subs r5,r2,#52'
ARM_INSTRUCTION_SET_armv5 = "arm"
ARM_INSTRUCTION_SET_armv4 = "arm"

DISABLE_STATIC = ""
