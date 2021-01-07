SUMMARY = "GNOME javascript bindings based on the Spidermonkey javascript engine"
HOMEPAGE = "https://wiki.gnome.org/Projects/Gjs"

LICENSE = "MIT & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=beb29cf17fabe736f0639b09ee6e76fa \
                    file://COPYING.LGPL;md5=3bf50002aefd002f49e7bb854063f7e7"

inherit gnomebase gettext gobject-introspection meson 

WARN_QA_remove = "unknown-configure-option"

export GIDATADIR="${STAGING_DATADIR_NATIVE}/gobject-introspection-1.0"

DEPENDS = "glib-2.0 gobject-introspection gobject-introspection-native cairo gtk+3 mozjs78 glib-2.0-native"

EXTRA_OEMESON = " -Dskip_dbus_tests=true -Dskip_gtk_tests=true -Dinstalled_tests=false -Dpkgconfig-sysroot-path=${GIDATADIR}"
CFLAGS_append = " -include ${STAGING_INCDIR}/mozjs-78/js/RequiredDefines.h"

SRC_URI += " file://0001-Fix-tests-that-cannot-run.patch"
SRC_URI[archive.md5sum] = "d43a936f5edd8ff36ecc54684b26d82a"
SRC_URI[archive.sha256sum] = "f30cf90e016db6c8fdd0059749559611760f0721f375b2b61e0b7239b43ab5f8"

RDEPENDS_${PN} += "libmozjs-78"

FILES_${PN}-dbg += "${datadir}/gjs-1.0/lsan ${datadir}/gjs-1.0/valgrind"
