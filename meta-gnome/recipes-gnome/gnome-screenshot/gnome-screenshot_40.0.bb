SUMMARY = "GNOME Screenshot"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gettext

DEPENDS += "libx11 libxext glib-2.0 gtk+3 libcanberra glib-2.0-native libxml2-native libhandy"

def gnome_verdir(v):
    return oe.utils.trim_version(v, 1)

SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive"

SRC_URI[archive.sha256sum] = "368ca95a39e39dc2406c849e8c4205e3f574acdd874c30741873455e3d21a5e2"

FILES_${PN} += "${datadir}/dbus-1/services"
FILES_${PN}-dev += "${datadir}/metainfo"

