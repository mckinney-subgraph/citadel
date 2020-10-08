SUMMARY = "GNOME library which provides API shared by several components and applications"
SECTION = "x11/gnome"
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=5f30f0716dfdd0d91eb439ebec522ec2"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gtk-icon-cache gconf mime pkgconfig upstream-version-is-even gobject-introspection

WARN_QA_remove = "unknown-configure-option"

SRC_URI[archive.md5sum] = "285d5d8a97b7564809151d401199c239"
SRC_URI[archive.sha256sum] = "007bbd48c1ca2fcb184713c923174c1d4328c3e33a2271ca536bdd3b71229bc6"

DEPENDS += "itstool-native gsettings-desktop-schemas gconf virtual/libx11 gtk+3 glib-2.0 startup-notification xkeyboard-config iso-codes udev libseccomp"

inherit gtk-doc

EXTRA_OEMESON = "-Ddesktop_docs=false -Dgnome_distributor='Subgraph'"

PACKAGES =+ "libgnome-desktop"
FILES_libgnome-desktop = "${libdir}/lib*${SOLIBS} ${datadir}/libgnome-desktop*/pnp.ids ${datadir}/gnome/*xml"

RRECOMMENDS_libgnome-desktop += "gsettings-desktop-schemas"


