
inherit meson pkgconfig

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7578fad101710ea2d289ff5411f1b818"

SRCREV = "238d1c078fb03338e9f271d98f7bf6b1fc399285"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/swaywm/wlroots;protocol=https \
           "

DEPENDS = "libx11 libdrm dbus libxcb xcb-util-wm xcb-util-image virtual/egl mesa wayland wayland-native libxkbcommon libinput systemd pixman"


