SUMMARY = "GNOME Display Manager"
LICENSE="GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = " \
    dconf-native \
    itstool-native \
    gtk+3 \
    glib-2.0 \
    accountsservice \
    libcanberra \
    libxinerama \
    libpam \
    plymouth \
"

REQUIRED_DISTRO_FEATURES = "x11 systemd pam"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gsettings gobject-introspection gettext systemd useradd upstream-version-is-even features_check

SRC_URI[archive.md5sum] = "948dec80099d9d3a2adacc5788964043"
SRC_URI[archive.sha256sum] = "46d4415c39c94ccee81fbac21102e2ebcd284d39858c893e759900b1e88435ba"

SRC_URI += "file://gdm.conf"

WARN_QA_remove = "unknown-configure-option"

FILES_${PN} += " \
    ${libdir}/systemd/system/gdm.service \
    ${libdir}/systemd/user \
    ${base_libdir}/security/pam_gdm.so \
    ${datadir}/gnome-session/sessions \
    ${datadir}/dconf/profile \
    /run/gdm/greeter \
"

do_install_append() {
    install -d ${D}${sysconfdir}/default/volatiles
    echo "d gdm gdm 755 ${localstatedir}/run/gdm/greeter none" > ${D}${sysconfdir}/default/volatiles/99_gdm
    rm ${D}${sysconfdir}/gdm/custom.conf
    rm -rf ${D}/run/gdm
    rm ${D}${libdir}/udev/rules.d/61-gdm.rules
    install -m 644 ${WORKDIR}/gdm.conf ${D}${sysconfdir}/gdm/custom.conf
}

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --home /var/lib/gdm -u 21 -s /bin/false --user-group gdm"

SYSTEMD_SERVICE_${PN} = "${BPN}.service"

EXTRA_OEMESON = "\
    -Dplymouth=enabled \
    -Dxdmcp=disabled \
    -Ddefault-pam-config=openembedded \
    -Dpam-mod-dir=${base_libdir}/security \
    -Dgdm-xsession=true\
"

PACKAGES += "${PN}-help"
FILES_${PN}-help = "${datadir}/help"
