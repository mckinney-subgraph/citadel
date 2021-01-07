SUMMARY = "Location and timezone database and weather lookup library"
HOMEPAGE = "https://wiki.gnome.org/Projects/LibGWeather"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI[archive.md5sum] = "d6081108f9c224c5cb594f8ccb025db9"
SRC_URI[archive.sha256sum] = "de2709f0ee233b20116d5fa9861d406071798c4aa37830ca25f5ef2c0083e450"

DEPENDS = "gtk+3 libxml2 libsoup-2.4 glib-2.0 itstool-native geocode-glib glib-2.0-native"

WARN_QA_remove = "unknown-configure-option"

FILES_${PN} += "${datadir}/glib-2.0/schemas"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gobject-introspection gettext
FILES_${PN} += "${datadir}/icons"

EXTRA_OEMESON = "--buildtype=release -Denable-introspection=true"

