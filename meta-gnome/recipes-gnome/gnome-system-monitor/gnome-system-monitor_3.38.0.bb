SUMMARY = "Gnome system monitor"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = " \
    gnome-common-native \
    libxml2-native \
    glib-2.0-native \
    gtkmm3 \
    libgtop \
    librsvg \
    polkit \
"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gnome-help itstool gtk-icon-cache features_check gettext upstream-version-is-even

SRC_URI[archive.md5sum] = "0494ca62d2d59b5de5efc2eddef58463"
SRC_URI[archive.sha256sum] = "73bf7ab4d5503c2567d76f831c9ae3fe27c31495761889ad3b351ef4c068aff4"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "-Dsystemd=true, -Dsystemd=false, systemd"

RRECOMMENDS_${PN} = "adwaita-icon-theme"

FILES_${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/metainfo \
"
