DESCRIPTION = "Intelligent Input Bus for Linux/Unix"
LICENSE = "LGPLv2.1"
DEPENDS = "prelink \
           glib-2.0 \
           gsettings-desktop-schemas \
           json-glib \
           gnome-desktop \
           gettext-native \
           intltool-native \
           dconf \
           libnotify \
           gconf-native \
           gtk+3 \
          "

LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

SRC_URI = " \
           https://github.com/ibus/ibus/releases/download/${PV}/${BPN}-${PV}.tar.gz \
           file://0001-strip-out-dbus-build-dep.patch \
           file://0002-decorate-automake-for-valaflags.patch \
	   file://0003-use-wayland-display-on-wayland.patch \
          "

SRC_URI[md5sum] = "717975f7de5b7a6eb89a5966db3e6c2e"
SRC_URI[sha256sum] = "8170eba58c28aa4818970751ebdeada728ebb63d535967a5c5f5c21b0017be4a"


S = "${WORKDIR}/${PN}-${PV}"

inherit autotools pkgconfig gtk-doc features_check vala gobject-introspection 

FILES_${PN} += "${datadir}"
FILES_${PN} += "${libdir}"

EXTRA_OECONF += " --disable-emoji-dict --disable-unicode-dict --disable-tests --disable-gtk2 "

do_configure_prepend() {
	touch ${S}/ChangeLog
	sed -i "s^@EXTRA_AM_VALAFLAGS@^--vapidir=${RECIPE_SYSROOT_NATIVE}${datadir}/vala-0.38/vapi --vapidir=${B}/bindings/vala --pkg gio-2.0^g" ${S}/tools/Makefile.am
	sed -i "s^@EXTRA_AM_VALAFLAGS@^--vapidir=${RECIPE_SYSROOT_NATIVE}${datadir}/vala-0.38/vapi --vapidir=${B}/bindings/vala --pkg gio-2.0^g" ${S}/engine/Makefile.am
	sed -i "s^@EXTRA_AM_VALAFLAGS@^--vapidir=${RECIPE_SYSROOT_NATIVE}${datadir}/vala-0.38/vapi --vapidir=${B}/bindings/vala --pkg gio-2.0^g" ${S}/ui/gtk3/Makefile.am
}

#do_install_append () {
#    rm ${D}${datadir}/applications/ibus-setup.desktop
#}
