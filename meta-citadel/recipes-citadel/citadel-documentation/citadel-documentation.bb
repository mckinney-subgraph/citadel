DESCRIPTION = "Citadel Yelp Documentation"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit allarch

SRC_URI = "\
    file://pages \
    file://citadel-documentation.desktop \
"

do_install() {
    install -m 0755 -d ${D}${datadir}/citadel-documentation
    install -m 0755 -d ${D}${datadir}/applications

    install -m 0644 ${WORKDIR}/pages/*.page ${D}${datadir}/citadel-documentation
    install -m 0644 ${WORKDIR}/citadel-documentation.desktop ${D}${datadir}/applications
}

FILES_${PN} = "/"
