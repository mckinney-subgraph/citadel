LICENSE = "GPLv2" 
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gobject-introspection gettext

WARN_QA_remove = "unknown-configure-option"

SRC_URI[archive.md5sum] = "059529c91a3d84e6542f662be47a657f"
SRC_URI[archive.sha256sum] = "0874ded4498e960b264baa54b6407a5a75dac54fc5b7cd60db91133e9c0260dd"

SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive \
           file://0001-Disabled-calendar-events-from-user-session.patch \
           file://0001-do-not-use-python-path-from-build-environment.patch \
           file://0001-do-not-build-calendar-server.patch \
           file://0001-Patch-gnome-shell-to-disable-logging-out.patch \
           "

DEPENDS = "glib-2.0-native systemd libcanberra gtk+3 glib-2.0 libxml2 libcroco mutter libsoup-2.4 gsettings-desktop-schemas gcr atk polkit startup-notification libx11 gnome-bluetooth libsecret networkmanager gjs mozjs network-manager-applet pulseaudio libxml2-native paxctl-native ibus xmlto-native sassc-native"

RDEPENDS_${PN} = "xserver-xorg-xwayland cantarell-fonts gnome-theme-adwaita gnome-theme-adwaita-dark gnome-control-center gnome-session dconf"

FILES_${PN} += "\
    ${datadir}/dbus-1 \
    ${datadir}/xdg-desktop-portal \
    ${datadir}/gnome-control-center/keybindings \
    ${systemd_user_unitdir} \
"

PACKAGES =+ "${PN}-tools"
FILES_${PN}-tools += "\
    /usr/bin/gnome-shell-perf-tool \
    /usr/bin/gnome-shell-extension-tool \
"

do_configure_append () {
    MUTTER_DIR="/usr/lib/mutter"
    sed --in-place=.old1 "s;=${MUTTER_DIR};=${PKG_CONFIG_SYSROOT_DIR}${MUTTER_DIR};" ${B}/build.ninja
}

do_install_append() {
    paxctl -cm ${D}${bindir}/gnome-shell
}

EXTRA_OEMESON += "-Dbrowser_plugin=false -Dman=false -Dsystemd=true -Dnetworkmanager=true"
