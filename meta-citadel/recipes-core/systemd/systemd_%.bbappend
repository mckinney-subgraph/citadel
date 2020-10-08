FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RDEPENDS_${PN}_remove = "systemd-serialgetty"
RDEPENDS_${PN}_remove = "volatile-binds"
RDEPENDS_${PN}_remove = "update-rc.d"

ALTERNATIVE_${PN}_remove = "resolv-conf"

GROUPADD_PARAM_${PN} += "; -r kvm"
PACKAGECONFIG = "\
    efi acl ldconfig pam usrmerge rfkill backlight binfmt hostnamed localed logind machined myhostname \
    nss polkit randomseed seccomp timedated utmp timesyncd kmod sysusers gshadow \
"

SRC_URI += "\
            file://50-udev-default.rules \
        "


do_install_append() {
    rm -f ${D}${sysconfdir}/tmpfiles.d/00-create-volatile.conf
    ln -s rescue.target ${D}${systemd_unitdir}/system/kbrequest.target
    rm -f ${D}${libdir}/udev/rules.d/50-udev-default.rules
    install -d ${D}{$libdir}/udev/rules.d
    install -m 644 ${WORKDIR}/50-udev-default.rules ${D}{$libdir}/udev/rules.d/
}
