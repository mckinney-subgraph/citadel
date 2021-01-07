SUMMARY = "GNOME Screenshot"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gettext gobject-introspection

WARN_QA_remove = "unknown-configure-option"

DEPENDS += "libx11 libxext glib-2.0 gtk+3 libcanberra glib-2.0-native libxml2-native libhandy"

SRC_URI[archive.md5sum] = "6550491108355c9d991e90b735ef3908"
SRC_URI[archive.sha256sum] = "e556d3dd134d91344d2857c066434bfb64f7c85bdec7bc33739366b9bcd29fc0"

FILES_${PN} += "${datadir}/dbus-1/services"
FILES_${PN}-dev += "${datadir}/metainfo"

