
inherit meson pkgconfig

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7578fad101710ea2d289ff5411f1b818"

SRC_URI = "https://github.com/swaywm/wlroots/archive/${PV}.tar.gz \
           "
SRC_URI[md5sum] = "d186d57cd7aeca3d8af10e2d88575875"
SRC_URI[sha256sum] = "3c6d422aaa7ac09a1e4a88d93f07a4d6ef6c5e4d76c3422c240a5783265ed0e3"

DEPENDS = "libx11 libdrm dbus libxcb xcb-util-wm xcb-util-image virtual/egl mesa wayland wayland-native libxkbcommon libinput systemd pixman"


