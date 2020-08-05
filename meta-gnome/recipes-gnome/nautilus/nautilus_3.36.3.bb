SUMMARY = "GNOME file manager"
HOMEPAGE = "https://wiki.gnome.org/action/show/Apps/Files"

LICENSE = "GPLv3 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504 \
                    file://libnautilus-extension/LICENSE;md5=321bf41f280cf805086dd5a720b37785"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gobject-introspection gettext

SRC_URI[archive.md5sum] = "c3c8dbb90d8eeed6c127aa568e131395"
SRC_URI[archive.sha256sum] = "b6cafc7ab1e70a64383de391b6097fcccbf36b208f8502d8c46423224fd30ef8"

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
