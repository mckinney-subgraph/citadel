
SUMMARY = "A set of daemons that manage and provide various parameters to applications"
HOMEPAGE = "https://wiki.gnome.org/Initiatives/Wayland/gnome-settings-daemon"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
                    file://COPYING.LIB;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = "pango gnome-desktop xorgproto libnotify fontconfig libgudev libxext wayland glib-2.0 libxi libx11 libwacom libxtst gsettings-desktop-schemas intltool-native gtk+3 polkit upower lcms glib-2.0-native wayland colord geoclue libcanberra geocode-glib libgweather pulseaudio networkmanager"

FILES_${PN} += "\
    ${systemd_user_unitdir} \
    ${libdir}/gnome-settings-daemon-40 \
"

FILES_${PN}-staticdev += "${libdir}/gnome-settings-daemon-3.0/libgsd.a"


GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gobject-introspection gettext meson-exe-wrapper

ERROR_QA_remove = "unknown-configure-option"

def gnome_verdir(v):
    return oe.utils.trim_version(v, 1)

SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive"

SRC_URI[archive.sha256sum] = "37dfac1b69a53e2e499228420259d6e134c1c06b74530af88fa855bda1187b21"
SRC_URI += "file://0001-disable-power-tests-and-sharing.patch"

EXTRA_OEMESON += "\
    --buildtype=release \
    -Dcups=false \
    -Dsmartcard=false \
    -Dwwan=false \
    -Dusb-protection=false \
    -Dsystemd=true \
"

