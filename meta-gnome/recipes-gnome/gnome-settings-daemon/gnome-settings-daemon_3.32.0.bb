
SUMMARY = "A set of daemons that manage and provide various parameters to applications"
HOMEPAGE = "https://wiki.gnome.org/Initiatives/Wayland/gnome-settings-daemon"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
                    file://COPYING.LIB;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = "pango gnome-desktop xorgproto libnotify fontconfig libgudev libxext wayland glib-2.0 libxi libx11 libwacom libxtst gsettings-desktop-schemas intltool-native gtk+3 polkit upower lcms glib-2.0-native wayland colord geoclue libcanberra geocode-glib libgweather pulseaudio networkmanager"

FILES_${PN} += "\
    ${libdir}/gnome-settings-daemon-3.0 \
"

FILES_${PN}-staticdev += "${libdir}/gnome-settings-daemon-3.0/libgsd.a"

SRC_URI[archive.md5sum] = "d1f55fb2059a2c52b1ce9af71f949961"
SRC_URI[archive.sha256sum] = "c3b8035aced4971ac49d3f3a5faa301a0825607e73248666a5f8078793d58397"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gobject-introspection gettext meson-exe-wrapper

SRC_URI += " file://0001-Disable-gsd-sharing-plugin.patch \
             file://0001-don-t-run-power-module-tests.patch"

EXTRA_OEMESON += "--buildtype=release -Dcups=false -Dsmartcard=false"

