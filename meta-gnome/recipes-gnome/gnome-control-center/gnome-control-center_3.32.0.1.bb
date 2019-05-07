SUMMARY = "GNOME desktop configuration UI"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=75859989545e37968a99b631ef42722e"

SRC_URI[archive.md5sum] = "31728ca3e3dc8d2e3a6cbdfd987b7901"
SRC_URI[archive.sha256sum] = "86795715bf439aafb5b74a42065ac1d86cac53075b1cc8044bc27a964e15c012"    

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gettext


DEPENDS = "pulseaudio colord-gtk networkmanager ibus colord gnome-desktop gnome-settings-daemon polkit libcanberra gdk-pixbuf fontconfig gtk+3 glib-2.0 intltool-native upower libpwquality cairo libxml2 libgudev libsoup-2.4 libxi libwacom libx11 libgtop gnome-common-native autoconf-archive-native wayland accountsservice modemmanager network-manager-applet gnome-bluetooth clutter-1.0 clutter-gtk gsettings-desktop-schemas glib-2.0-native libxml2-native udisks2 gsound"

#
# Extra options have been added to meson_options.txt to make some components of the control
# center optional.  One reason is that these components drag in heavy dependencies, and some
# of these dependencies have not been packaged and tested yet.
# 
# Gnome Online Accounts support
#
#      EXTRA_OEMESON += "-Donline_accounts=true"
#      DEPENDS += "grilo gnome-online-accounts webkitgtk rest"
#
# Printer Panel
#
#      EXTRA_OEMESON += "-Dcups=true"
#      DEPENDS += "cups samba"  (only smbclient needed from samba)
#
# User Accounts Panel
#
#      EXTRA_OEMESON += "-Duser_accounts=true"
#      DEPENDS += "accountsservice krb5"
# Citadel
#
#      This option disables some things in gnome-control-center such as certain 
#      panels that are not used in Citadel, defaults to true in meson_options. 
#      To re-enable these things, set the option to false:
#      
#      EXTRA_OEMESON += "-Dcitadel=false"
SRC_URI += "  file://0001-Make-GOA-CUPS-and-User-Accounts-optional.patch \
              file://0001-Remove-unneeded-panels.patch"

FILES_${PN} += "\
    ${datadir}/bash-completion \
    ${datadir}/gettext \
    ${datadir}/dbus-1 \
    ${datadir}/gnome-shell/search-providers \
    ${datadir}/metainfo \
"

EXTRA_OEMESON = "--buildtype=release -Dcheese=false -Ddocumentation=false -Dstaging_dir=${STAGING_DIR_TARGET}"
