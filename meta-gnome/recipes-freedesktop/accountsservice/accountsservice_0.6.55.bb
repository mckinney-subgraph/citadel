SUMMARY = "D-Bus service for accessing the list of user accounts and information attached to those accounts."
HOMEPAGE = "https://www.freedesktop.org/wiki/Software/AccountsService"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://www.freedesktop.org/software/accountsservice/accountsservice-${PV}.tar.xz"
SRC_URI[md5sum] = "6e4c6fbd490260cfe17de2e76f5d803a"
SRC_URI[sha256sum] = "ff2b2419a7e06bd9cb335ffe391c7409b49a0f0130b890bd54692a3986699c9b"

DEPENDS = "glib-2.0 intltool-native polkit systemd glib-2.0-native dbus" 

inherit meson pkgconfig gettext gobject-introspection

FILES_${PN} += "\
    ${datadir}/dbus-1/interfaces/*.xml \
    ${datadir}/dbus-1/system-services/org.freedesktop.Accounts.service \
    ${datadir}/polkit-1/actions/org.freedesktop.accounts.policy \
    ${systemd_system_unitdir} \
"
EXTRA_OEMESON = "-Dsystemd=true"

