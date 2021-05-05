SUMMARY = "Multi-platform toolkit for creating GUIs"
DESCRIPTION = "GTK+ is a multi-platform toolkit for creating graphical user interfaces. Offering a complete \
set of widgets, GTK+ is suitable for projects ranging from small one-off projects to complete application suites."
HOMEPAGE = "http://www.gtk.org"
BUGTRACKER = "https://bugzilla.gnome.org/"
SECTION = "libs"

DEPENDS = "glib-2.0 cairo pango atk jpeg libpng gdk-pixbuf libepoxy graphene libxi sassc-native \
           gdk-pixbuf-native wayland wayland-protocols libxkbcommon virtual/egl virtual/libgles2 wayland-native"

LICENSE = "LGPLv2 & LGPLv2+ & LGPLv2.1+"


GNOMEBASEBUILDCLASS = "meson"
GIR_MESON_ENABLE_FLAG = "enabled"
GIR_MESON_DISABLE_FLAG = "disbled"
GTKDOC_MESON_OPTION = "gtk_doc"

inherit gnomebase gettext pkgconfig gtk-doc update-alternatives gtk-immodules-cache gsettings features_check gobject-introspection

# versions >= 3.90 are development versions, otherwise like upstream-version-is-even
UPSTREAM_CHECK_REGEX = "[^\d\.](?P<pver>3\.([1-8]?[02468])+(\.\d+)+)\.tar"

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

# This should be in autotools.bbclass, but until something elses uses it putting
# it here avoids rebuilding everything.
export PKG_CONFIG_FOR_BUILD = "${STAGING_BINDIR_NATIVE}/pkg-config-native"

do_configure_prepend() {
    # Do this because the configure script is running ./libtool directly
    rm -f libtool
    ln -s ${TARGET_PREFIX}libtool libtool
    #delete a file that will get confused with generated one in ${B}
    rm -f ${S}/gtk/gtktypefuncs.c
}

EXTRA_OEMESON += " \
    -Ddemos=false \
"

do_compile_prepend() {
        export GIR_EXTRA_LIBS_PATH="${B}/gdk/.libs"
}

LIBV = "4.0.0"


FILES_${PN}_append = " ${datadir}/gtk-4.0/emoji "

FILES_${PN}-dev += "\
        ${datadir}/gtk-4.0/valgrind \
        ${datadir}/gtk-4.0/gtk4builder.rng \
        ${datadir}/gettext/its \
"

RDEPENDS_${PN}-dev += "${@bb.utils.contains("PACKAGECONFIG", "wayland", "wayland-protocols", "", d)}"


python populate_packages_prepend () {
    import os.path

    gtk_libdir = d.expand('${libdir}/gtk-4.0/${LIBV}')
    immodules_root = os.path.join(gtk_libdir, 'immodules')
    printmodules_root = os.path.join(gtk_libdir, 'printbackends');

    immodules = do_split_packages(d, immodules_root, r'^im-(.*)\.so$', 'gtk4-immodule-%s', 'GTK input module for %s')
    if immodules:
        d.setVar("GTKIMMODULES_PACKAGES", " ".join(immodules))

    do_split_packages(d, printmodules_root, r'^libprintbackend-(.*)\.so$', 'gtk4-printbackend-%s', 'GTK printbackend module for %s')

    if (d.getVar('DEBIAN_NAMES')):
        d.setVar(d.expand('PKG_${PN}'), '${MLPREFIX}libgtk-4.0')
}
