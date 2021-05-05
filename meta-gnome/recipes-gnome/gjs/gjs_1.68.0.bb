SUMMARY = "GNOME javascript bindings based on the Spidermonkey javascript engine"
HOMEPAGE = "https://wiki.gnome.org/Projects/Gjs"

LICENSE = "MIT & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8dcea832f6acf45d856abfeb2d51ec48"

inherit gnomebase gettext gobject-introspection meson 

ERROR_QA_remove = "unknown-configure-option"

export GIDATADIR="${STAGING_DATADIR_NATIVE}/gobject-introspection-1.0"

DEPENDS = "glib-2.0 gobject-introspection gobject-introspection-native cairo gtk+3 mozjs78 glib-2.0-native"

EXTRA_OEMESON = " -Dskip_dbus_tests=true -Dskip_gtk_tests=true -Dinstalled_tests=false -Dpkgconfig-sysroot-path=${GIDATADIR}"
CFLAGS_append = " -include ${STAGING_INCDIR}/mozjs-78/js/RequiredDefines.h"

SRC_URI += " file://0001-Fix-tests-that-cannot-run.patch"
SRC_URI[archive.sha256sum] = "f00e74a00e81ab61bb92669e0f1c8bb613cc019586097f06aed0572efcaf1aef"

RDEPENDS_${PN} += "libmozjs-78"

FILES_${PN}-dbg += "${datadir}/gjs-1.0/lsan ${datadir}/gjs-1.0/valgrind"
