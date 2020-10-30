SUMMARY = "Bluetooth integration with GNOME desktop"
HOMEPAGE = "https://wiki.gnome.org/Projects/GnomeBluetooth"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a \
                    file://COPYING.LIB;md5=a6f89e2100d9b6cdffcea4f398e37343"

SRC_URI[archive.md5sum] = "c82f7df6a411c897d4d59337d5d9a789"
SRC_URI[archive.sha256sum] = "0a068e3bddbbdab46991521e6624098579abe80da242398bdd579c4ca6926422"


DEPENDS = "glib-2.0 glib-2.0-native gtk+3 libcanberra libnotify libxml2-native gobject-introspection"

WARN_QA_remove = "unknown-configure-option"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gobject-introspection gettext
FILES_${PN} += "${datadir}/icons"

EXTRA_OEMESON = "--buildtype=release -Denable-introspection=true"

