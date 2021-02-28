
inherit meson pkgconfig

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e316e9609dd7672b87ff25b46b2cf3e1"

SRC_URI = "git://github.com/emersion/grim;protocol=https"

UPSTREAM_CHECK_COMMITS = "1" 

SRCREV = "a5e009a87005810321c7c43c149384b8f25e51b8"

S = "${WORKDIR}/git"

DEPENDS = "wayland wayland-native wayland-protocols cairo"


