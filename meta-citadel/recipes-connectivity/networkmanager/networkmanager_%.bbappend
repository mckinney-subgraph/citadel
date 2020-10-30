# Append recipe from meta-openembedded/meta-networking

SRC_URI += "\
    file://watch-resolvconf.path \
    file://watch-resolvconf.service \
"

SYSTEMD_SERVICE_${PN} += "watch-resolvconf.path"

do_install_append() {
    install -m 644 ${WORKDIR}/watch-resolvconf.path ${D}${systemd_system_unitdir}
    install -m 644 ${WORKDIR}/watch-resolvconf.service ${D}${systemd_system_unitdir}
}
