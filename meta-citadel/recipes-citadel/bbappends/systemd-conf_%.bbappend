CONFFILES_${PN} += "${sysconfdir}/machine-id"

do_install_append() {
    install -d ${D}${sysconfdir}
    echo "a4e415feff81466c925aab34b0c35a3c" > ${D}${sysconfdir}/machine-id
}
