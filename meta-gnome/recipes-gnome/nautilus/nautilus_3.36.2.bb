SUMMARY = "GNOME file manager"
HOMEPAGE = "https://wiki.gnome.org/action/show/Apps/Files"

LICENSE = "GPLv3 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504 \
                    file://libnautilus-extension/LICENSE;md5=321bf41f280cf805086dd5a720b37785"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gobject-introspection gettext

SRC_URI[archive.md5sum] = "232e60565be545d4aca737f6a89514c9"
SRC_URI[archive.sha256sum] = "26673c9599502452cf299358729be8b554066a9b670d89b24e245160d15776fa"


FILES_${PN} += "\
    ${datadir}/glib-2.0/schemas \
    ${datadir}/gnome-shell/search-providers \
    ${datadir}/metainfo/org.gnome.Nautilus.appdata.xml \
    ${datadir}/icons/hicolor/ \
    ${datadir}/dbus-1/services \
"

DEPENDS += "gtk+3 glib-2.0 pango gnome-autoar libxml2 gnome-desktop tracker gexiv2"

EXTRA_OEMESON = "-Dselinux=false -Dpackagekit=false -Dextensions=false -Dintrospection=true"

do_install_append () {
    rm ${D}${datadir}/applications/nautilus-autorun-software.desktop
}
