SUMMARY = "GNOME Display Manager"
LICENSE="GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = " \
    dconf-native \
    gtk+3 \
    glib-2.0 \
    accountsservice \
    libcanberra \
    libxinerama \
    libpam \
    plymouth \
"

REQUIRED_DISTRO_FEATURES = "x11 systemd pam"

inherit gnomebase gsettings gobject-introspection gettext systemd useradd upstream-version-is-even distro_features_check

SRC_URI[archive.md5sum] = "65d1fd4bb85e8b133d1cbffb80ecf62e"
SRC_URI[archive.sha256sum] = "e85df657aa8d9361af4fb122014d8f123a93bfe45a7662fba2b373d839dbd8d3" 

SRC_URI += "\ 
            file://gdm.conf \
            file://0001-replace-absolute-path-with-staging-path.patch \
            "

# Some gnome components - as gnome-panel and gnome-shell (!!) - require gdm
# components. To allow gnome-images using different display-manager, split them
# out into a seperate package.
#PACKAGE_BEFORE_PN = "${PN}-base"
#FILES_${PN}-base = " \
#    ${datadir}/glib-2.0 \
#    ${datadir}/gnome-session \
#    ${libdir}/lib*${SOLIBS} \
#    ${libdir}/girepository-1.0 \
#"

#CONFFILES_${PN} += "${sysconfdir}/gdm/custom.conf"
FILES_${PN} += " \
    ${libdir}/systemd/system/gdm.service \ 
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

EXTRA_OECONF = " \
    --with-plymouth \
    --with-default-pam-config=openembedded \
    --with-pam-mod-dir=${base_libdir}/security \
    --enable-introspection \
    --enable-systemd-journal \ 
    --enable-wayland-support \
    --with-xdmcp=no \
    --disable-static \
    --enable-gdm-xsession \
    --with-initial-vt=7 \
"

