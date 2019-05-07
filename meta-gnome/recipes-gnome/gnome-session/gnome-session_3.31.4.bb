SUMMARY = "GNOME session management"
HOMEPAGE = "https://wiki.gnome.org/Projects/SessionManagement"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase meson 

SRC_URI += " file://0001-Remove-org.gnome.SettingsDaemon.Sharing-from-require.patch"
SRC_URI[archive.md5sum] = "f2d548a79732f0db9f6148de908c68ce"
SRC_URI[archive.sha256sum] = "5279776b9f8b74e348507fedb062ef303172fbb1f3a16979a8cb9321c396878e"

DEPENDS = "glib-2.0-native intltool-native xmlto-native glib-2.0 gnome-desktop json-glib"
RDEPENDS_${PN} = "gnome-settings-daemon"

FILES_${PN} += "\
    ${datadir}/xsessions \
    ${datadir}/glib-2.0/schemas \
    ${datadir}/GConf \
    ${datadir}/wayland-sessions \
"
EXTRA_OEMESON = "-Denable-docbook=false -Denable-man=false"

