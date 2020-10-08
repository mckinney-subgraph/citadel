SUMMARY = "Password and keyring managing daemon"
HOMEPAGE = "http://www.gnome.org/"
BUGTRACKER = "https://bugzilla.gnome.org/"
SECTION = "x11/gnome"

LICENSE = "GPLv2+ & LGPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

DEPENDS = " \
    glib-2.0-native \
    gtk+3 \
    gcr \
    libgcrypt \
"

inherit gnomebase gsettings distro_features_check remove-libtool gettext upstream-version-is-even

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI[archive.md5sum] = "38f0732845a510a8dff4f154c3406f65"
SRC_URI[archive.sha256sum] = "a264b57a8d1a71fdf0d66e8cd6033d013fb828be279c35766545eb9bb3734f87"


EXTRA_OECONF = " \
    --disable-doc \
    --disable-ssh-agent \
"


FILES_${PN} += " \
    ${datadir}/dbus-1/services \
    ${datadir}/p11-kit \
    ${base_libdir}/security/*${SOLIBSDEV} \
    ${libdir}/pkcs11/gnome-keyring-pkcs11.so \
"

# fix | gnome-keyring-daemon: insufficient process capabilities, unsecure memory might get used
# This does not make it through pseudo so perform on-target - sigh
#pkg_postinst_ontarget_${PN} () {
#    setcap cap_ipc_lock+ep `which gnome-keyring-daemon`
#}
#RDEPENDS_${PN} += "libcap-bin"
