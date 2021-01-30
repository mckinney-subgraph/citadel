SUMMARY = "GNOME library which provides API shared by several components and applications"
SECTION = "x11/gnome"
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=5f30f0716dfdd0d91eb439ebec522ec2"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gtk-icon-cache gconf mime pkgconfig upstream-version-is-even gobject-introspection

WARN_QA_remove = "unknown-configure-option"

SRC_URI += "file://0001-needs-stdint-include.patch"
SRC_URI[archive.sha256sum] = "bfe00257b2bb59fff04ac1c144804084d1c253e66f9d7408f6060bbf7ad1d831"

DEPENDS += "itstool-native gsettings-desktop-schemas gconf virtual/libx11 gtk+3 glib-2.0 startup-notification xkeyboard-config iso-codes udev libseccomp"

inherit gtk-doc

EXTRA_OEMESON = "-Ddesktop_docs=false -Dgnome_distributor='Subgraph'"

PACKAGES =+ "libgnome-desktop"
FILES_libgnome-desktop = "${libdir}/lib*${SOLIBS} ${datadir}/libgnome-desktop*/pnp.ids ${datadir}/gnome/*xml"

RRECOMMENDS_libgnome-desktop += "gsettings-desktop-schemas"


