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

def gnome_verdir(v):
    return oe.utils.trim_version(v, 1)

SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive"

SRC_URI[archive.sha256sum] = "5ed74b739d3a232ffb1e4a132bcf965bdfe955d995b528773588775b02f2950e"

SRC_URI += "file://gdm.conf"

ERROR_QA_remove = "unknown-configure-option"

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
