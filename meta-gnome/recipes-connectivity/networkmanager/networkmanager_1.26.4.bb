SUMMARY = "NetworkManager"
HOMEPAGE = "https://wiki.gnome.org/Projects/NetworkManager"
SECTION = "net/misc"

LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c \
"

DEPENDS = " \
    intltool-native \
    libxslt-native \
    libnl \
    libgudev \
    util-linux \
    libndp \
    libnewt \
    curl \
    dbus \
    nss \
    iwd \
    coreutils-native \
    python3-pygobject-native \
"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gettext update-rc.d systemd bash-completion vala gobject-introspection gtk-doc update-alternatives upstream-version-is-even

SRC_URI = "${GNOME_MIRROR}/NetworkManager/${@gnome_verdir("${PV}")}/NetworkManager-${PV}.tar.xz \
           file://${BPN}.initd \
           file://NetworkManager.conf \
           file://watch-resolvconf.path \
           file://watch-resolvconf.service \
           file://0001-Generator-fails-to-build-in-cross-compile-environmen.patch \
           "

SRC_URI[sha256sum] = "7995802c67e54fc58be4c0e3d2095d943f53f4300a93bde7ff48822a0b1f62ea"

S = "${WORKDIR}/NetworkManager-${PV}"

EXTRA_OEMESON = "\
    -Diwd=true \
    -Dnmtui=false \
    -Djson_validation=false \
    -Dselinux=false \
    -Dlibaudit=no \
    -Dpolkit=false \
    -Dppp=false \
    -Dmodem_manager=false \
    -Dovs=false \
    -Dlibpsl=false \
    -Dqt=false \
    -Dfirewalld_zone=false \
    -Dsession_tracking_consolekit=false \
    -Ddhclient=${base_sbindir}/dhclient \
    "

do_compile_prepend() {
    export GIR_EXTRA_LIBS_PATH="${B}/libnm/.libs:${B}/libnm-glib/.libs:${B}/libnm-util/.libs"
}

PACKAGES =+ " \
  ${PN}-nmtui ${PN}-nmtui-doc \
  ${PN}-adsl ${PN}-cloud-setup \
"

SYSTEMD_PACKAGES = "${PN} ${PN}-cloud-setup"

FILES_${PN}-adsl = "${libdir}/NetworkManager/${PV}/libnm-device-plugin-adsl.so"

FILES_${PN}-cloud-setup = " \
    ${libexecdir}/nm-cloud-setup \
    ${systemd_system_unitdir}/nm-cloud-setup.service \
    ${systemd_system_unitdir}/nm-cloud-setup.timer \
    ${libdir}/NetworkManager/dispatcher.d/90-nm-cloud-setup.sh \
    ${libdir}/NetworkManager/dispatcher.d/no-wait.d/90-nm-cloud-setup.sh \
"
ALLOW_EMPTY_${PN}-cloud-setup = "1"

FILES_${PN} += " \
    ${libexecdir} \
    ${libdir}/NetworkManager/${PV}/*.so \
    ${libdir}/NetworkManager \
    ${nonarch_libdir}/NetworkManager/conf.d \
    ${nonarch_libdir}/NetworkManager/dispatcher.d \
    ${nonarch_libdir}/NetworkManager/dispatcher.d/pre-down.d \
    ${nonarch_libdir}/NetworkManager/dispatcher.d/pre-up.d \
    ${nonarch_libdir}/NetworkManager/dispatcher.d/no-wait.d \
    ${nonarch_libdir}/NetworkManager/VPN \
    ${nonarch_libdir}/NetworkManager/system-connections \
    ${datadir}/polkit-1 \
    ${datadir}/dbus-1 \
    ${nonarch_base_libdir}/udev/* \
    ${systemd_system_unitdir} \
    ${libdir}/pppd \
"

RRECOMMENDS_${PN} += "iptables \
    ${@bb.utils.filter('PACKAGECONFIG', 'dnsmasq', d)} \
"
RCONFLICTS_${PN} = "connman"

FILES_${PN}-dev += " \
    ${datadir}/NetworkManager/gdb-cmd \
    ${libdir}/pppd/*/*.la \
    ${libdir}/NetworkManager/*.la \
    ${libdir}/NetworkManager/${PV}/*.la \
"

FILES_${PN}-nmtui = " \
    ${bindir}/nmtui \
    ${bindir}/nmtui-edit \
    ${bindir}/nmtui-connect \
    ${bindir}/nmtui-hostname \
"

FILES_${PN}-nmtui-doc = " \
    ${mandir}/man1/nmtui* \
"

INITSCRIPT_NAME = "network-manager"
SYSTEMD_SERVICE_${PN} = "NetworkManager.service NetworkManager-dispatcher.service"

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES','systemd','resolv-conf','',d)}"
ALTERNATIVE_TARGET[resolv-conf] = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${sysconfdir}/resolv-conf.NetworkManager','',d)}"
ALTERNATIVE_LINK_NAME[resolv-conf] = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${sysconfdir}/resolv.conf','',d)}"

do_install_append() {
    install -Dm 0755 ${WORKDIR}/${BPN}.initd ${D}${sysconfdir}/init.d/network-manager

    rm -rf ${D}/run ${D}${localstatedir}/run

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        # For read-only filesystem, do not create links during bootup
        ln -sf ../run/NetworkManager/resolv.conf ${D}${sysconfdir}/resolv-conf.NetworkManager

        # systemd v210 and newer do not need this rule file
        rm ${D}/${nonarch_base_libdir}/udev/rules.d/84-nm-drivers.rules
    fi
    install -d ${D}${sysconfdir}/NetworkManager/
    install -m 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/
}
