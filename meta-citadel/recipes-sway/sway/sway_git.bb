LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dfc67e5b1fa10ebb4b70eb0c0ca67bea"

SRCREV = "bcde298a719f60b9913133dbd2a169dedbc8dd7d"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/swaywm/sway;protocol=https \
           file://config \
           "

UPSTREAM_CHECK_COMMITS = "1"

inherit meson 

DEPENDS = "dbus cairo pango wlroots libinput libxkbcommon wayland wayland-native libpam libcap json-c libpcre gdk-pixbuf"

FILES_${PN} += "\
	${datadir}/wayland-sessions/sway.desktop \
"

do_install_append() {
    rm ${D}${sysconfdir}/sway/config
    install -m 644 ${WORKDIR}/config ${D}${sysconfdir}/sway/config
}

EXTRA_OEMESON += "-Ddefault-wallpaper=false -Dzsh-completions=false -Dbash-completions=false -Dfish-completions=false" 


