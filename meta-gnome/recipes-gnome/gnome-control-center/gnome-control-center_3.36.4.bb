SUMMARY = "GNOME Settings"
DESCRIPTION = "GNOME Settings is GNOME's main interface for configuration of various aspects of your desktop"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=75859989545e37968a99b631ef42722e"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gsettings gettext vala upstream-version-is-even bash-completion distro_features_check

DEPENDS = " \
    gdk-pixbuf-native \
    colord-gtk \
    udisks2 \
    upower \
    polkit \
    pulseaudio \
    accountsservice \
    gsettings-desktop-schemas \
    gnome-settings-daemon \
    gnome-desktop \
    networkmanager \
    network-manager-applet \
    gnome-bluetooth \
    libgtop \
    libgudev \
    gsound \
    libpwquality \
    ibus \
"

REQUIRED_DISTRO_FEATURES += " pulseaudio systemd x11"
SRC_URI += " file://0001-Add-options-for-Citadel-and-disabling-GOA-CUPS.patch"

SRC_URI[archive.md5sum] = "16c228d7de4e9d2d57550791fbca3390"
SRC_URI[archive.sha256sum] = "ac02346bcf3391aa5c86ed857d76689fdb6e43c2b4b20d3ec6eab0ea9fecf754"

#SRC_URI += "  file://0001-Remove-unneeded-panels-make-GOA-CUPS-optional.patch"

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
#SRC_URI += "  file://0001-Make-GOA-CUPS-and-User-Accounts-optional.patch \       
#              file://0001-Remove-unneeded-panels.patch"

FILES_${PN} += " \
    ${datadir}/dbus-1 \
    ${datadir}/gnome-shell \
    ${datadir}/metainfo \
"

FILES_${PN}-dev += "${datadir}/gettext"

RDEPENDS_${PN} += "gsettings-desktop-schemas"
EXTRA_OEMESON = "--buildtype=release -Dcheese=false -Ddocumentation=false -Dstaging_dir=${STAGING_DIR_TARGET}"

