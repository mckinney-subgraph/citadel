SUMMARY = "Plymouth is a project from Fedora providing a flicker-free graphical boot process."

DESCRIPTION = "Plymouth is an application that runs very early in the boot process \
    (even before the root filesystem is mounted!) that provides a \
    graphical boot animation while the boot process happens in the background. \
"

HOMEPAGE = "http://www.freedesktop.org/wiki/Software/Plymouth"
SECTION = "base"

LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "libcap libpng cairo dbus udev"
PROVIDES = "virtual/psplash"
RPROVIDES_${PN} = "virtual-psplash virtual-psplash-support"

SRC_URI = "http://www.freedesktop.org/software/plymouth/releases/${BPN}-${PV}.tar.xz file://plymouthd.conf file://subgraph.png"
SRC_URI[md5sum] = "8a25d23f3ae732af300a56fa33cacff2"
SRC_URI[sha256sum] = "ecae257f351d098340542a5bc06de029404c24dcee87e6ebb2abd5ef117fce86"

EXTRA_OECONF += " --enable-shared --disable-static --disable-gtk --disable-documentation \
    --with-logo=${LOGO} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--enable-systemd-integration', '', d)} \
"

PACKAGECONFIG ??= "pango"
PACKAGECONFIG_append_x86 = " drm"
PACKAGECONFIG_append_x86-64 = " drm"

PACKAGECONFIG[drm] = "--enable-drm,--disable-drm,libdrm"
PACKAGECONFIG[pango] = "--enable-pango,--disable-pango,pango"
PACKAGECONFIG[gtk] = "--enable-gtk,--disable-gtk,gtk+"
#PACKAGECONFIG[initrd] = ",,,"

LOGO = "${WORKDIR}/subgraph.png"

inherit autotools pkgconfig systemd gettext

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${B}/systemd-units/*.service ${D}${systemd_unitdir}/system
    install -m 644 ${B}/systemd-units/systemd-ask-password-plymouth.path ${D}${systemd_unitdir}/system
    # Remove /var/run from package as plymouth will populate it on startup
    rm -fr "${D}${localstatedir}/run"

    rm -rf ${D}/etc/plymouth/plymouthd.conf
    install -d ${D}/etc/plymouth
    install -m 644 ${WORKDIR}/plymouthd.conf ${D}/etc/plymouth/plymouthd.conf

#    if ! ${@bb.utils.contains('PACKAGECONFIG', 'initrd', 'true', 'false', d)}; then
        rm -rf "${D}${libexecdir}"
#    fi

    # https://patchwork.openembedded.org/patch/146656/
    sed -i 's#ExecStart= -#ExecStart=/usr/bin/systemd-tty-ask-password-agent -#' ${D}${systemd_unitdir}/system/systemd-ask-password-plymouth.service

    # https://aur.archlinux.org/packages/plymouth/#comment-613012
    printf "RuntimeDirectory=plymouth\n" >> ${D}${systemd_unitdir}/system/systemd-ask-password-plymouth.service
    
}

#PACKAGES =. "${@bb.utils.contains('PACKAGECONFIG', 'initrd', '${PN}-initrd ', '', d)}"
PACKAGES =+ "${PN}-set-default-theme"

#FILES_${PN}-initrd = "${libexecdir}/plymouth/*"
FILES_${PN}-set-default-theme = "${sbindir}/plymouth-set-default-theme"

FILES_${PN} += "${systemd_unitdir}/system/*"
FILES_${PN}-dbg += "${libdir}/plymouth/renderers/.debug"


#RDEPENDS_${PN}-initrd = "bash dracut"
RDEPENDS_${PN}-set-default-theme = "bash"

SYSTEMD_SERVICE_${PN} = "plymouth-start.service"
