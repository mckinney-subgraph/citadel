SUMMARY = "A library full of GTK widgets for mobile phones"
HOMEPAGE = "https://gitlab.gnome.org/GNOME/libhandy"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "glib-2.0 glib-2.0-native libxml2-native gtk+3"
GIR_MESON_ENABLE_FLAG = "enabled"
GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase vala gobject-introspection

SRC_URI[archive.md5sum] = "3cdc0b2274b41770ad4758e612f4c16d"
SRC_URI[archive.sha256sum] = "a9398582f47b7d729205d6eac0c068fef35aaf249fdd57eea3724f8518d26699"

