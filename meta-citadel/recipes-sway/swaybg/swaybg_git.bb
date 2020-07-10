
inherit meson pkgconfig

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b53d9ec16b9125995437ac9efab1b450"

SRC_URI = "git://github.com/swaywm/swaybg;protocol=https"
PV = "1.0+git${SRCPV}"
PR = "r0"

UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "a8f109af90353369e7e2e689efe8ce06eb9c60ac"

S = "${WORKDIR}/git"

DEPENDS = "wayland wayland-native wayland-protocols cairo"


