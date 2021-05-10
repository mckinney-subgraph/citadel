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
    docbook-xsl-stylesheets-native \
"

inherit gnomebase gsettings gnome-help gettext itstool upstream-version-is-even

SRC_URI[archive.sha256sum] = "0770cc7320d737fb2eaf9e4cf5ecc6a741f120b17b192f061f683e14b869d8e4"

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
