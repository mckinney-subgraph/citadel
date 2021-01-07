LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d791728a073bc009b4ffaf00b7599855"

SRC_URI = "git://github.com/home-sweet-gnome/dash-to-panel.git;protocol=https"
SRCREV = "48a69e529614d1da456802b818e7d7f0d4d1d642"

S = "${WORKDIR}/git"

DEPENDS = "gettext-native glib-2.0-native"
FILES_${PN} = "${datadir}/gnome-shell/extensions"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake install 'DESTDIR=${D}'
}

