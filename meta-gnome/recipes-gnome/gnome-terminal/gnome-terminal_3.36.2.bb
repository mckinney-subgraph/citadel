SUMMARY = "GNOME terminal emulator"
HOMEPAGE = "https://wiki.gnome.org/Apps/Terminal"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

DEPENDS = " \
    glib-2.0-native \
    intltool-native \
    yelp-tools-native \
    desktop-file-utils-native \
    gtk+3 \
    gsettings-desktop-schemas \
    vte \
    dconf \
    libpcre2 \
"

inherit gnomebase gsettings gettext upstream-version-is-even

SRC_URI[archive.md5sum] = "08150cbf2e23dd4f60f959a6eca8ef0c"
SRC_URI[archive.sha256sum] = "41d1b6a3dc97c066e294acdb7f36931e81668638dcc92ffa25bca3ddebacdf46"

EXTRA_OECONF = "--disable-search-provider --without-nautilus-extension"

FILES_${PN} += "\
    ${systemd_user_unitdir} \
    ${datadir}/dbus-1/services \
    ${datadir}/glib-2.0/schemas \
    ${datadir}/metainfo \
"

FILES_${PN}-doc += "\
    ${datadir}/help \
"
