LICENSE = "GPLv2" 
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gettext gobject-introspection gobject-introspection-data distro_features_check bash-completion

REQUIRED_DISTRO_FEATURES = "x11 systemd pam"

WARN_QA_remove = "unknown-configure-option"

SRC_URI[archive.md5sum] = "f0cd44e573a82531b4556afa624b1193"
SRC_URI[archive.sha256sum] = "3b129b2a1f1f1654a101098ea8b91a73a836aad7d166f52dd46b1c6037ec1753"


SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive \
           file://0001-Disabled-calendar-events-from-user-session.patch \
           file://0001-do-not-use-python-path-from-build-environment.patch \
           file://0001-Remove-calendar-server-fix-build.patch \
           "

DEPENDS = " \
    libxml2-native \
    sassc-native \
    gtk+3 \
    mutter \
    gcr \
    gjs \
    mozjs68 \
    network-manager-applet \
    gnome-autoar \
    polkit \
    libcroco \
    startup-notification \
    ibus \
    gsettings-desktop-schemas \
"

RDEPENDS_${PN} = "gsettings-desktop-schemas librsvg-gtk"

FILES_${PN} += "\
    ${datadir}/dbus-1 \
    ${datadir}/xdg-desktop-portal \
    ${datadir}/gnome-control-center  \
    ${datadir}/bash-completion/completions/gnome-extensions \
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

#do_install_append() {
#    paxctl -cm ${D}${bindir}/gnome-shell
#}

EXTRA_OEMESON += "-Dman=false -Dsystemd=true -Dnetworkmanager=true -Dextensions_app=false"
