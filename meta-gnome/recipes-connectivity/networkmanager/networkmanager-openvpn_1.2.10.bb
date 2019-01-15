SUMMARY = "NetworkManager-openvpn-plugin"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=100d5a599bead70ddcd70dcd73f2e29c"

DEPENDS = "dbus dbus-glib networkmanager openvpn intltool-native glib-2.0-native"

inherit gnomebase useradd gettext systemd

SRC_URI = "${GNOME_MIRROR}/NetworkManager-openvpn/${@gnome_verdir("${PV}")}/NetworkManager-openvpn-${PV}.tar.xz"

SRC_URI[md5sum] = "f118226ed2bfbacfd64ac4d1e0bd0383"
SRC_URI[sha256sum] = "ac86a7a539d78df90095676e9183f2d422fb93dbfe4b3afef22f81825d303d61"

S = "${WORKDIR}/NetworkManager-openvpn-${PV}"

PACKAGECONFIG[gnome] = "--with-gnome,--without-gnome"

do_install_append () {
    rm -rf ${D}${libdir}/NetworkManager/*.la
}

# Create user and group nm-openvpn that are needed since version 1.0.6
USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system nm-openvpn"

FILES_${PN} += " \
    ${libdir}/NetworkManager/*.so \
    ${nonarch_libdir}/NetworkManager/VPN/nm-openvpn-service.name \
"

FILES_${PN}-staticdev += " \
    ${libdir}/NetworkManager/*.a \
"

RDEPENDS_${PN} = " \
    networkmanager \
    openvpn \
"
