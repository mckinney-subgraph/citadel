SUMMARY = "Default GNOME desktop background images"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=75859989545e37968a99b631ef42722e"

SRC_URI[archive.md5sum] = "822d5ca042f5143903bee7711ceaaa92"
SRC_URI[archive.sha256sum] = "f7712a873a80c9a9fcf3952611effeb2d9aed23a3e8abfcda8afb15c427d1ee3"

FILES_${PN} += "\
    ${datadir}/backgrounds/gnome \
    ${datadir}/gnome-background-properties \
"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gettext
