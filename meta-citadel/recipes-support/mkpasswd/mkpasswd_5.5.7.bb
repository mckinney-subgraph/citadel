LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/rfc1036/whois.git;protocol=https"
SRCREV = "7f9fdae2a99c9a56959b37049488f94e8bdfa7c2"

S = "${WORKDIR}/git"

DEPENDS = "libxcrypt libidn2"
RDEPENDS_${PN} = "libxcrypt libidn2"
inherit pkgconfig gettext

do_compile() {
    oe_runmake CC_FOR_BUILD="${BUILD_CC}" BASEDIR=${D}
}

do_install() {
    oe_runmake install-mkpasswd DESTDIR=${D} BASEDIR=${D}
}
