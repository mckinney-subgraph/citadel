SUMMARY = "GNOME disk utility"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SECTION = "x11/gnome"

DEPENDS = " \
    gtk+3 \
    libdvdread \
    libcanberra \
    libnotify \
    libsecret \
    libpwquality \
    udisks2 \
    libxml2-native \
"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gtk-icon-cache gettext features_check upstream-version-is-even mime-xdg

REQUIRED_DISTRO_FEATURES = "x11"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"

# As soon as elogind is of interest this needs rework: meson option is combo
PACKAGECONFIG[systemd] = "-Dlogind=libsystemd,-Dlogind=none,systemd"

EXTRA_OEMESON += "-Dman=false"
SRC_URI[archive.sha256sum] = "7734ce668d9ec31286abd7944f95190210288fbc54704f05bc55cbd0340b5223"

FILES_${PN} += " \
    ${datadir}/metainfo \
    ${datadir}/dbus-1 \
"

