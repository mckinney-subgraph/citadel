SUMMARY = "Location and timezone database and weather lookup library"
HOMEPAGE = "https://wiki.gnome.org/Projects/LibGWeather"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit meson pkgconfig gobject-introspection gettext

def gnome_verdir(v):
    return oe.utils.trim_version(v, 1)

GNOME_COMPRESS_TYPE ?= "xz"
GNOMEBN ?= "${BPN}"
SRC_URI[archive.sha256sum] = "ca4e8f2a4baaa9fc6d75d8856adb57056ef1cd6e55c775ba878ae141b6276ee6"
SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive"

DEPENDS = "gtk+3 libxml2 libsoup-2.4 glib-2.0 python3-pygobject-native itstool-native geocode-glib glib-2.0-native"

ERROR_QA_remove = "unknown-configure-option"

FILES_${PN} += "${datadir}/glib-2.0/schemas"


#EXTRA_OEMESON = "--buildtype=release -Denable-introspection=true"

SECTION ?= "x11/gnome"

FILES_${PN} += "${datadir}/application-registry  \
                ${datadir}/mime-info \
                ${datadir}/mime/packages \
                ${datadir}/mime/application \
                ${datadir}/gnome-2.0 \
                ${datadir}/polkit* \
                ${datadir}/GConf \
                ${datadir}/glib-2.0/schemas \
                ${datadir}/appdata \
                ${datadir}/icons \
"

FILES_${PN}-doc += "${datadir}/devhelp"

do_install_append() {
	rm -rf ${D}${localstatedir}/lib/scrollkeeper/*
	rm -rf ${D}${localstatedir}/scrollkeeper/*
	rm -f ${D}${datadir}/applications/*.cache
}
