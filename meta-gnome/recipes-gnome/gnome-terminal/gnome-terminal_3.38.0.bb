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

SRC_URI[archive.md5sum] = "947facc65c12735ac586e9dbf6228502"
SRC_URI[archive.sha256sum] = "0a0fc7a8b383c6ffd61469be1dea5ba63cffad812921780e7fad40c2e2ae54f5"

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
