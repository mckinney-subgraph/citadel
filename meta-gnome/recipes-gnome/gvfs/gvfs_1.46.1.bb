DESCRIPTION = "gvfs is a userspace virtual filesystem"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=05df38dd77c35ec8431f212410a3329e"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gtk-icon-cache gconf mime bash-completion gettext upstream-version-is-even

DEPENDS += "libsecret glib-2.0 gconf libgudev udisks2 polkit shadow-native libusb1 gsettings-desktop-schemas"

SRC_URI = "https://download.gnome.org/sources/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive"

SRC_URI[archive.md5sum] = "3013dca31a373202779d0f9652118fe2"
SRC_URI[archive.sha256sum] = "621ea6c1b9a60b7ed2893938620d3190725a3d9dc65ce5af0fb6c186ee342503"

EXTRA_OEMESON = " \
    -Dadmin=false \
    -Dafc=false \
    -Dafp=false \
    -Darchive=false \
    -Dcdda=false \
    -Ddnssd=false \
    -Dgoa=false \
    -Dgoogle=false \
    -Dgphoto2=false \
    -Dhttp=false \
    -Dmtp=false \
    -Dhttp=false \
    -Dnfs=false \
    -Dsftp=false \
    -Dsmb=false \
    -Dbluray=false \
    -Dgcr=false \
    -Dkeyring=false \
    -Dudisks2=true \
"

FILES_${PN} += " \
    ${datadir}/glib-2.0 \
    ${datadir}/GConf \
    ${datadir}/dbus-1/services \
    ${libdir}/gio/modules/*.so \
    ${libdir}/tmpfiles.d \
    ${systemd_user_unitdir} \
"

RDEPENDS_${PN} = "udisks2"

FILES_${PN}-dbg += "${libdir}/gio/modules/.debug/*"
FILES_${PN}-dev += "${libdir}/gio/modules/*.la"

PACKAGECONFIG[systemd] = "-Dsystemduserunitdir=${systemd_user_unitdir} -Dtmpfilesdir=${libdir}/tmpfiles.d, -Dsystemduserunitdir=no -Dtmpfilesdir=no, systemd"

# needs meta-filesystems
PACKAGECONFIG[fuse] = "-Dfuse=true, -Dfuse=false, fuse"

do_install_append() {

    # After rebuilds (not from scracth) it can happen that the executables in
    # libexec ar missing executable permission flag. Not sure but it came up
    # during transition to meson. Looked into build files and logs but could
    # not find suspicious
    for exe in `find ${D}/${libexec}`; do
       chmod +x $exe
    done

    # Something sets user services executable - looks a bit as if meson
    # transition has room for enhancements...
    # Systemd warns with messages as:
    # Apr 07 01:00:33 raspberrypi3 systemd[348]: Configuration file /usr/lib/systemd/user/gvfs-mtp-volume-monitor.service is marked executable. Please remove executable permissio>
    for service in `find ${D}/${systemd_user_unitdir}`; do
       chmod -x $service
    done
}
