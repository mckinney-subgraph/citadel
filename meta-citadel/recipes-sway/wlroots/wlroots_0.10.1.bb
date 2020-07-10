
inherit meson pkgconfig

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7578fad101710ea2d289ff5411f1b818"

SRC_URI = "https://github.com/swaywm/wlroots/archive/${PV}.tar.gz \
           "

SRC_URI[md5sum] = "3d9736cfbfaf6661d36b6c788ac2c199"
SRC_URI[sha256sum] = "b84baefbaff7bb04b3d2c43cbacef1a433e2cd65111f8fbf4bfc5faaa4b34b08"

DEPENDS = "libx11 libdrm dbus libxcb xcb-util-wm xcb-util-image virtual/egl mesa wayland wayland-native libxkbcommon libinput systemd pixman"


