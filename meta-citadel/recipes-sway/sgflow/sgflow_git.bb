
inherit meson pkgconfig

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f0ae30b1ae6ac2aff356f848cfe6bd04"

SRC_URI = "git://github.com/subgraph/sgflow;protocol=https \
           "
PV = "0.1.0+git${SRCPV}"
PR = "r0"

UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "3a2d4a70f09a3cee6d345ed7fbb17696576a0e4c"

S = "${WORKDIR}/git"

#DEPENDS = "libx11 libdrm dbus libxcb xcb-util-wm xcb-util-image virtual/egl mesa wayland wayland-native libxkbcommon libinput systemd pixman"

DEPENDS = "wlroots gtk+3 glib-2.0-native wayland-native"


