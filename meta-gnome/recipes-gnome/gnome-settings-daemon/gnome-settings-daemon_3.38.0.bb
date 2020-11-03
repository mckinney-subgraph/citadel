
SUMMARY = "A set of daemons that manage and provide various parameters to applications"
HOMEPAGE = "https://wiki.gnome.org/Initiatives/Wayland/gnome-settings-daemon"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
                    file://COPYING.LIB;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = "pango gnome-desktop xorgproto libnotify fontconfig libgudev libxext wayland glib-2.0 libxi libx11 libwacom libxtst gsettings-desktop-schemas intltool-native gtk+3 polkit upower lcms glib-2.0-native wayland colord geoclue libcanberra geocode-glib libgweather pulseaudio networkmanager"

FILES_${PN} += "\
    ${systemd_user_unitdir} \
    ${libdir}/gnome-settings-daemon-3.0 \
"

FILES_${PN}-staticdev += "${libdir}/gnome-settings-daemon-3.0/libgsd.a"

SRC_URI[archive.md5sum] = "69988449f4e1be6b26a0109cc6019d47"
SRC_URI[archive.sha256sum] = "b7ed5b5fab9aad192ceed6a95be2805124e3c63c46891bbae076f7ec3fd6792e"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gobject-introspection gettext meson-exe-wrapper

WARN_QA_remove = "unknown-configure-option"

SRC_URI += "file://0001-disable-power-tests-and-sharing.patch"

# allow cross build mixed with build of native tools
do_write_config_append() {
    cat >${WORKDIR}/meson.native <<EOF
[binaries]
pkgconfig = 'pkg-config-native'
EOF
}

EXTRA_OEMESON += "\
    --buildtype=release \
    --native-file ${WORKDIR}/meson.native \
    -Dcups=false \
    -Dsmartcard=false \
    -Dwwan=false \
    -Dusb-protection=false \
    -Dsystemd=true \
"

