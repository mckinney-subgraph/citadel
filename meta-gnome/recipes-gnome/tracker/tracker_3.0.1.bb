SUMMARY = "Desktop search engine and metadata storage system"
HOMEPAGE = "https://wiki.gnome.org/Projects/Tracker"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=506ab4920510c723c01689e212f41404 \
                    file://COPYING.GPL;md5=ee31012bf90e7b8c108c69f197f3e3a4 \
                    file://COPYING.LGPL;md5=2d5025d4aa3495befef8f17206a5b0a1 \
                    file://src/libtracker-common/COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
                    file://src/libtracker-data/COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
                    file://docs/reference/COPYING;md5=f51a5100c17af6bae00735cd791e1fcc"

GNOMEBASEBUILDCLASS = "meson"
inherit gettext pkgconfig python3native gnomebase vala gobject-introspection
SRC_URI[archive.md5sum] = "173668f5d1a890a494d681457ac7029b"
SRC_URI[archive.sha256sum] = "39a3a326f4708a65f3f48771465b93097d25d697069e41e1b947bdaecad10ce6"

WARN_QA_remove = "unknown-configure-option"

DEPENDS = "intltool-native libunistring sqlite3 dbus upower networkmanager bash-completion glib-2.0-native libsoup-2.4 libxml2 json-glib"

# Insert a line into meson.cross under [properties] header:
#
#  [properties]
#  sqlite3_has_fts5 = 'true'
#
# Fixes this:
#
# ../tracker-3.0.1/meson.build:99:2: ERROR: Unknown cross property: sqlite3_has_fts5.
#

do_write_config_append() {
    sed -i -e "/^\[properties\]/a sqlite3_has_fts5 = 'true'" ${WORKDIR}/meson.cross
}

FILES_${PN} += "\
    ${libdir}/systemd/user/tracker-store.service \
    ${libdir}/systemd/user \
    ${datadir}/glib-2.0/schemas \
    ${datadir}/bash-completion/completions \
    ${datadir}/dbus-1/services \
    ${datadir}/tracker3 \
"

FILES_${PN}-dev += "\
    ${datadir}/vala/vapi \
"
#FILES_${PN}-staticdev += "\
#    ${libdir}/tracker-2.0/libtracker-data.a \
#    ${libdir}/tracker-2.0/libtracker-common.a \
#"
INSANE_SKIP_${PN} += "dev-so"

EXTRA_OEMESON = "\
    -Ddocs=false \
    -Dman=false \
    -Dnetwork_manager=disabled \
    -Dbash_completion=false \
    -Dtest_utils=false \
"
