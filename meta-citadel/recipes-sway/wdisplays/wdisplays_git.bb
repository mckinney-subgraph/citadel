
inherit meson pkgconfig

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSES/MIT.txt;md5=38aa75cf4c4c87f018227d5ec9638d75 \
                    file://LICENSES/GPL-3.0-or-later.txt;md5=1c76c4cc354acaac30ed4d5eefea7245 \
                    file://LICENSES/CC0-1.0.txt;md5=6fd064768b8d61c31ddd0540570fbd33 \
                    file://LICENSES/CC-BY-SA-4.0.txt;md5=4b39cb11ffa11c92d3f7a3431390e0d9"

SRC_URI = "git://github.com/cyclopsian/wdisplays;protocol=https"
PV = "1.0+git${SRCPV}"
PR = "r0"

UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "dd7e1e22ee04cf37ecc8b48cdb0c103de18e8761"

S = "${WORKDIR}/git"

DEPENDS = "wayland wayland-native wayland-protocols glib-2.0-native gtk+3 libepoxy"

FILES_${PN} += "${datadir}/icons/hicolor/scalable/apps/wdisplays.svg"
