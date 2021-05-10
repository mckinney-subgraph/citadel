# Append recipe from meta-openembedded/meta-networking
FILESEXTRAPATHS_prepend := "${THISDIR}/networkmanager:"

SRC_URI += "\
    file://NetworkManager.conf \
    file://watch-resolvconf.path \
    file://watch-resolvconf.service \
"

SYSTEMD_SERVICE_${PN} += "watch-resolvconf.path"

do_install_append() {
    install -m 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/
    install -m 644 ${WORKDIR}/watch-resolvconf.path ${D}${systemd_system_unitdir}
    install -m 644 ${WORKDIR}/watch-resolvconf.service ${D}${systemd_system_unitdir}
}
PACKAGECONFIG = "nss systemd polkit"
PACKAGECONFIG[wifi] = ""
EXTRA_OECONF = "\
    --with-iwd \
    --enable-wifi=yes \
    --without-iptables \
"
