SUMMARY = "PAM pwdfile library"
SECTION = "libs"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI = "git://github.com/tiwe-de/libpam-pwdfile.git;protocol=https"
SRCREV = "8f0e412b48178c00abd023917dd2c9050ee89c18"

S = "${WORKDIR}/git"

DEPENDS = "libpam libxcrypt"
inherit lib_package pkgconfig

FILES_${PN} += "${libdir}/security/pam_pwdfile.so"

do_compile_class() {
    oe_runmake CC_FOR_BUILD="${BUILD_CC}" PAM_LIB_DIR=${libdir}/security
}

do_install() {
    oe_runmake install DESTDIR=${D} PAM_LIB_DIR=${libdir}/security
}
