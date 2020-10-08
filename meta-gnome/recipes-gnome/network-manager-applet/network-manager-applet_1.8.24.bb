SUMMARY = "Applet for managing network connections"
HOMEPAGE = "https://wiki.gnome.org/Projects/NetworkManager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "gcr iso-codes networkmanager libgudev gtk+3 intltool-native modemmanager glib-2.0 libnotify libsecret glib-2.0-native"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gettext gobject-introspection

SRC_URI[archive.md5sum] = "5c1bf351fde5adc12200345550516050"
SRC_URI[archive.sha256sum] = "118bbb8a5027634b62e8b45b16ceafce74441529c99bf230654e3bec38f9fbbf"

FILES_${PN} += "${datadir}/metainfo"

EXTRA_OEMESON = "-Dwwan=false -Dteam=false -Dgcr=false -Dmobile_broadband_provider_info=false -Dgtk_doc=false -Dintrospection=true -Dselinux=false"
