
inherit meson pkgconfig

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e316e9609dd7672b87ff25b46b2cf3e1"

SRC_URI = "git://github.com/emersion/slurp;protocol=https"
UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "bfff873301e92364df80ef6aaa4a6b14f76177af"

S = "${WORKDIR}/git"

DEPENDS = "wayland wayland-native wayland-protocols cairo libxkbcommon"


