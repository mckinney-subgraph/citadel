SUMMARY = "GNOME desktop-wide GSettings schemas"
HOMEPAGE = "http://live.gnome.org/gsettings-desktop-schemas"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "glib-2.0 intltool-native"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gsettings gettext gobject-introspection upstream-version-is-even

EXTRA_OEMESON = "-Dintrospection=true"

SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive"
SRC_URI[archive.md5sum] = "465a98f5ab8a5ca896009228257fc40d"
SRC_URI[archive.sha256sum] = "5704c8266004b296036671f223c705dc046aa694a1b1abb87c67e7d2747a8c67"

