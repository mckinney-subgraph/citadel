DESCRIPTION = ""
HOMEPAGE = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=e6faad78a557b22780e4cf62c89976f8"
SECTION = ""
DEPENDS = ""

SRC_URI = "git://github.com/chriskempson/base16-vim;protocol=https"
SRCREV = "6191622d5806d4448fa2285047936bdcee57a098"

FILES_${PN} = "${datadir}/vim/colors"
S = "${WORKDIR}/git"

inherit allarch

do_configure () {
    :
}

do_compile() {
    :
}

do_install() {
    mkdir -p ${D}${datadir}/vim/colors
    install -Dm644 ${S}/colors/*.vim ${D}${datadir}/vim/colors
}
