
LICENSE = "LGPLv3 & MIT & GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE_LGPL.txt;md5=3000208d539ec061b899bce1d9ce9404 \
                    file://LICENSE_MIT.txt;md5=d015f272ca1521139fd99ea64d6e5e23 \
                    file://LICENSE_GPL.txt;md5=1ebbd3e34237af26da5dc08a4e440464 \
                    file://test/license-ignore.txt;md5=6d7a7aa8c01eace0202bf3b11070a899 \
                    file://test/check-licenses.py;md5=3c4fd5f6d979f739fba5d1f020f81e3e"


SRC_URI = "git://github.com/wmww/gtk-layer-shell;protocol=https"

ERROR_QA_remove = "unknown-configure-option"

SRCREV = "3b3b935b1643eb58e6cf5a9432b4470d568bfbc2"
UPSTREAM_CHECK_COMMITS = "1"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit meson pkgconfig gobject-introspection 
DEPENDS = "wlroots wayland wayland-native wayland-protocols glib-2.0 gtk+3"
#DEPENDS_class-native = "wayland-native glib-2.0-native gtk+3-native gtk+3-wayland-native"
BBCLASSEXTEND = "native nativesdk"

