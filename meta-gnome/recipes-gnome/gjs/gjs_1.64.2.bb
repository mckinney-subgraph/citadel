SUMMARY = "GNOME javascript bindings based on the Spidermonkey javascript engine"
HOMEPAGE = "https://wiki.gnome.org/Projects/Gjs"

LICENSE = "MIT & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=beb29cf17fabe736f0639b09ee6e76fa \
                    file://COPYING.LGPL;md5=3bf50002aefd002f49e7bb854063f7e7"

inherit gnomebase gettext gobject-introspection meson 

WARN_QA_remove = "unknown-configure-option"

export GIDATADIR="${STAGING_DATADIR_NATIVE}/gobject-introspection-1.0"

DEPENDS = "glib-2.0 gobject-introspection gobject-introspection-native cairo gtk+3 mozjs68 glib-2.0-native"

EXTRA_OEMESON = " -Dskip_dbus_tests=true -Dskip_gtk_tests=true -Dinstalled_tests=false -Dpkgconfig-sysroot-path=${GIDATADIR}"

SRC_URI += " file://0001-Fix-tests-that-cannot-run.patch"
SRC_URI[archive.md5sum] = "bd2f7f411b46016e8ee2d2c8d3c140d9"
SRC_URI[archive.sha256sum] = "15ff834d374df19595d955f03e6b60631a3bb14fabda36d00f81ab3eabd3997b"

RDEPENDS_${PN} += "libmozjs-68"

FILES_${PN}-dbg += "${datadir}/gjs-1.0/lsan ${datadir}/gjs-1.0/valgrind"
