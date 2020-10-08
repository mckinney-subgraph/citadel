GNOMEBASEBUILDCLASS = "meson"

inherit meson pkgconfig gnomebase gobject-introspection

DESCRIPTION = "Graphene"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a7d871d9e23c450c421a85bb2819f648"


SRC_URI = "${GNOME_MIRROR}/${GNOMEBN}/${@gnome_verdir("${PV}")}/${GNOMEBN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive"
SRC_URI[archive.md5sum] = "07f72436bc7a85d12f5edd9fcedd0184"
SRC_URI[archive.sha256sum] = "406d97f51dd4ca61e91f84666a00c3e976d3e667cd248b76d92fdb35ce876499"

DEPENDS = "gobject-introspection gobject-introspection-native"
#RDEPENDS_${PN} = "gobject-introspection"
BBCLASSEXTEND = "native"

EXTRA_OEMESON = "-Dintrospection=true -Dinstalled_tests=false -Dtests=false -Darm_neon=false"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} += "dev-so"

do_install() {
    install -d ${D}${includedir}/graphene-1.0/
    install -m 0644 ${WORKDIR}/build/include/*.h ${D}${includedir}/graphene-1.0/
    install -m 0644 ${S}/include/*.h ${D}${includedir}/
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/build/meson-private/graphene-gobject-1.0.pc ${D}${libdir}/pkgconfig/
    install -m 0644 ${WORKDIR}/build/meson-private/graphene-1.0.pc ${D}${libdir}/pkgconfig/
    install -d ${D}${datadir}/gir-1.0/
    install -m 0644 ${WORKDIR}/build/src/Graphene-1.0.gir ${D}/${datadir}/gir-1.0/
    install -d ${D}${libdir}/girepository-1.0/
    install -m 0644 ${WORKDIR}/build/src/Graphene-1.0.typelib ${D}/${libdir}/girepository-1.0/
    install -m 0644 ${WORKDIR}/build/src/libgraphene-1.0.* ${D}/${libdir}/
}

FILES_${PN} += "${includedir}/graphene-1.0/graphene-config.h ${includedir}/graphene.h ${libdir}/*.so.*"
FILES_${PN}-dev += "${libdir}/pkgconfig/graphene-gobject-1.0.pc ${libdir}/pkgconfig/graphene-1.0.pc ${datadir}/Graphene-1.0.gir $[libdir}/girepository-1.0/Graphene-1.0.typelib"
