SUMMARY = "SpiderMonkey is Mozilla's JavaScript engine written in C/C++"
HOMEPAGE = "https://developer.mozilla.org/en-US/docs/Mozilla/Projects/SpiderMonkey"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dc9b6ecd19a14a54a628edaaf23733bf"

# Path: /teams/releng/tarballs-needing-help/mozjs/mozjs-78.0.1gnome.tar.xz
SRC_URI = "https://download.gnome.org/teams/releng/tarballs-needing-help/mozjs/mozjs-78.0.1gnome.tar.xz;downloadfilename=mozjs78-78.0.1.tar.xz \
           file://avoid-running-autoconf2.13.patch \
           file://0001-Force-the-correct-target-string-with-environment-var.patch \
           file://0003-Set-rust-host-and-target-correctly.patch \
           file://0004-Do-not-add-RequiredDefines.h-here-instead-add-to-gjs.patch \
           "

SRC_URI[md5sum] = "09bf510150144238dd31f7f4e1582cf8"
SRC_URI[sha256sum] = "78d762012be9eb460b5805da4f919d1a12b15f4040f126c98a42c4dddac7339e"
S = "${WORKDIR}/mozjs-78.0.1gnome"

inherit autotools pkgconfig perlnative python3native
inherit rust-common 
inherit features_check 
CONFLICT_DISTRO_FEATURES_mipsarchn32 = "ld-is-gold"
TOOLCHAIN = "clang"

DEPENDS += "nspr zlib rust-native cargo-native clang-native cbindgen-native nasm-native yasm-native python3"

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
    --disable-debug-symbols \
    ${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', "--enable-gold", '--disable-gold', d)} \
"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)}"
PACKAGECONFIG[x11] = "--x-includes=${STAGING_INCDIR} --x-libraries=${STAGING_LIBDIR},--x-includes=no --x-libraries=no,virtual/libx11"

EXTRA_OEMAKE_task-compile += "OS_LDFLAGS='-Wl,-latomic ${LDFLAGS}'"
EXTRA_OEMAKE_task-install += "STATIC_LIBRARY_NAME=js_static"


export TMP="${B}"
export RUSTC="${WORKDIR}/recipe-sysroot-native/usr/bin/rustc"
export RUST_HOST="${BUILD_SYS}"
export RUST_TARGET="${RUST_TARGET_SYS}"
export RUST_TARGET_PATH="${STAGING_LIBDIR_NATIVE}/rustlib"
export BINDGEN_MFLOAT="${@bb.utils.contains('TUNE_CCARGS_MFLOAT', 'hard', '-mfloat-abi=hard', '', d)}"
export BINDGEN_CFLAGS="--target=${RUST_TARGET_SYS} --sysroot=${RECIPE_SYSROOT} ${BINDGEN_MFLOAT}"
export INSTALL_SDK="0"
export STAGING_LIBDIR
export NO_RUST_PANIC_HOOK="1"

#
# Silence a host leak while linking native binary (nsinstall_real) used during the
# mozjs build. This should be fixed, but I was unable to find a way to do so.
#
# log.do_compile:
#
#    recipe-sysroot-native/usr/bin/x86_64-oe-linux/x86_64-oe-linux-clang -std=gnu99 -o nsinstall_real -DXP_UNIX -O3  host_nsinstall.o host_pathsub.o
#
#    recipe-sysroot-native/usr/bin/x86_64-oe-linux-ld:
#
#        warning: library search path "/usr/lib/gcc/x86_64-linux-gnu/9" is unsafe for cross-compilation
#        warning: library search path "/usr/lib/gcc/x86_64-linux-gnu/9/../../../x86_64-linux-gnu" is unsafe for cross-compilation
#        warning: library search path "/usr/lib/gcc/x86_64-linux-gnu/9/../../../lib64" is unsafe for cross-compilation
#        warning: library search path "/usr/lib/gcc/x86_64-linux-gnu/9/../../.." is unsafe for cross-compilation
#
WARN_QA_remove = "compile-host-path"

setup_pythonpath() {
    export SHELL="/bin/sh"
    cd ${S}
    PYTHONPATH="${S}/config:${S}/build"
    for mod in which pytoml jsmin distro six pyyaml/lib3; do
        PYTHONPATH="$PYTHONPATH:${S}/third_party/python/${mod}"
    done

    for sub_dir in python testing/mozbase; do
        for module_dir in `ls $sub_dir -1`;do
            if [ -d "${S}/$sub_dir/$module_dir" ];then
                PYTHONPATH="$PYTHONPATH:${S}/$sub_dir/$module_dir"
            fi
        done
    done
    export PYTHONPATH
    cd ${B}
}

do_configure() {
    setup_pythonpath
    mkdir -p "${B}/_virtualenvs"
    ln -s "../../recipe-sysroot-native/usr/lib" "${B}/_virtualenvs/lib"
    ${S}/js/src/configure ${EXTRA_OECONF}
}

do_compile_prepend() {
    setup_pythonpath
}

do_install_prepend() {
    setup_pythonpath
}

do_install_append() {
    rm -f ${D}/${libdir}/libjs.a
}

PACKAGES =+ "lib${BPN}"
FILES_lib${BPN} += "${libdir}/lib*.so"

FILES_${PN}-dev += "${bindir}/js78-config"
RPROVIDES_lib${BPN} += "libmozjs-78"

DISABLE_STATIC = ""
