SUMMARY = "configuation database system"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"
SECTION = "x11/gnome"

SRC_URI[archive.md5sum] = "716cf730995cf133c2c443556a66a50c"
SRC_URI[archive.sha256sum] = "45f60f41330d27715cce1315af123f94f1c2cdedb68b6bed3b309866eec44f58"

DEPENDS = "dbus glib-2.0 xmlto-native glib-2.0-native"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase bash-completion vala

SRC_URI += "file://0001-meson.build-do-not-compile-docs.patch"

FILES_${PN} += " \
    ${datadir}/dbus-1 \
    ${libdir}/gio/modules/*.so \
"

EXTRA_OEMESON_append_class-native = "-Dbash_completion=false"

BBCLASSEXTEND= "native"
