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

inherit gnomebase gettext gsettings upstream-version-is-even distro_features_check

REQUIRED_DISTRO_FEATURES = "x11 systemd pam gobject-introspection-data"

SRC_URI += " file://0001-Disable-sharing-USB-protection-add-systemd-to-.deskt.patch"
SRC_URI[archive.md5sum] = "cd23e30c4991ca1f477020c67ea3a540"
SRC_URI[archive.sha256sum] = "d54b38b818c812f64b82cc6a1279e3ca5a6e391ee662793322a38cab5670bb7a"

EXTRA_OEMESON += "-Dsystemd=true -Dsystemd_session=enable -Dsystemd_journal=true -Ddocbook=false -Dman=false"
FILES_${PN} += " \
    ${bindir}/gnome-session \
    ${libexecdir}/gnome-session-ctl \
    ${datadir}/xsessions \
    ${datadir}/wayland-sessions \
    ${systemd_user_unitdir} \
"

RDEPENDS_${PN} += "gnome-shell gnome-settings-daemon gsettings-desktop-schemas"
