hostname="subgraph"

dirs1777_remove = "${localstatedir}/volatile/tmp"

dirs755="/boot /dev /usr/bin /usr/sbin /usr/lib /etc /etc/default /etc/skel /usr/lib /mnt /proc /home/root /run /usr /usr/bin /usr/share/doc/base-files-3.0.14 /usr/include /usr/lib /usr/sbin /usr/share /usr/share/common-licenses /usr/share/info /usr/share/man /usr/share/misc /var /sys /home /media"

volatiles = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/base-files:"
SRC_URI += "file://profile"

do_install_append () {
    rm ${D}${sysconfdir}/fstab
    rm ${D}${sysconfdir}/skel/.bashrc
    rm ${D}${sysconfdir}/skel/.profile
    install -m 0644 ${WORKDIR}/profile ${D}${sysconfdir}/profile
    sed -i 's#ROOTHOME#${ROOT_HOME}#' ${D}${sysconfdir}/profile
    sed -i 's#@BINDIR@#${bindir}#g' ${D}${sysconfdir}/profile
}
