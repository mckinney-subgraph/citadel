SUMMARY = "GNOME Usage"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gobject-introspection gettext vala

WARN_QA_remove = "unknown-configure-option"

#SRC_URI += "file://0001-Added-machine-tags.patch"

DEPENDS = "glib-2.0 glib-2.0-native gtk+3 gobject-introspection libgtop libdazzle libhandy tracker"
SRC_URI[archive.md5sum] = "a331e1b6d8f588c02440be1972097c62"
SRC_URI[archive.sha256sum] = "94d58202fd92094ee2a2647ea3f96d0b16b5f5d7f9bf5ae99f1c33117d1a1a57"


FILES_${PN} += "${datadir}/metainfo"

EXTRA_OEMESON = "--buildtype=release -Denable-introspection=true"

