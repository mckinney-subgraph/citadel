PACKAGECONFIG = "odirect udev"

# files/lvm.conf is customized to prevent writing to /etc
FILESEXTRAPATHS_prepend := "${THISDIR}/lvm2:"


#FILES_${PN}-dev += "${libdir}/liblvm2cmd.so*"
