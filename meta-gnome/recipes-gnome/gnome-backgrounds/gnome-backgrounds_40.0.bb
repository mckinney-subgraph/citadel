SUMMARY = "Default GNOME desktop background images"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=75859989545e37968a99b631ef42722e"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gettext

def gnome_verdir(v):
    return oe.utils.trim_version(v, 1)

SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive"

SRC_URI[archive.sha256sum] = "46c3133a5ed7180347ae396962bf8827085f0f3b7cd9385452d623c4a167a2f6"

FILES_${PN} += "\
    ${datadir}/backgrounds/gnome \
    ${datadir}/gnome-background-properties \
"

