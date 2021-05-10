SUMMARY = "GNOME session"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = " \
    glib-2.0-native \
    libxslt-native \
    xmlto-native \
    xtrans \
    libice \
    libsm \
    virtual/libx11 \
    gtk+3 \
    gnome-desktop \
    gsettings-desktop-schemas \
    json-glib \
"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gettext gsettings upstream-version-is-even features_check

REQUIRED_DISTRO_FEATURES = "x11 systemd pam gobject-introspection-data"

def gnome_verdir(v):
    return oe.utils.trim_version(v, 1)

SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive"

SRC_URI[archive.sha256sum] = "9c787829ee32e13e1508b9aee2b1d9ba42a02c48e6c8094e34f3e7f92af4df82"
SRC_URI += " \ 
    file://0001-Patch-locking-logout-so-it-does-not-disable-shutting.patch \
    file://0001-Disable-sharing-and-usb-protection-add-systemd-to-de.patch \
"

EXTRA_OEMESON += "-Dsystemd=true -Dsystemd_journal=true -Ddocbook=false -Dman=false"
FILES_${PN} += " \
    ${bindir}/gnome-session \
    ${libexecdir}/gnome-session-ctl \
    ${datadir}/xsessions \
    ${datadir}/wayland-sessions \
    ${systemd_user_unitdir} \
"

RDEPENDS_${PN} += "gnome-shell gnome-settings-daemon gsettings-desktop-schemas"
