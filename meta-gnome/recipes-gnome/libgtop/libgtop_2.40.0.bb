SUMMARY = "Library for accessing information about running processes"
HOMEPAGE = "https://developer.gnome.org/libgtop/stable/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552" 

DEPENDS = "libx11 glib-2.0 libxau"

inherit gnomebase perlnative gettext pkgconfig autotools

SRC_URI[archive.md5sum] = "c6d67325cd97b2208b41e07e6cc7b947"
SRC_URI[archive.sha256sum] = "78f3274c0c79c434c03655c1b35edf7b95ec0421430897fb1345a98a265ed2d4"
SRC_URI += " file://0001-Fix-hostleak-in-link-path.patch"

EXTRA_OECONF = "--with-examples=no"

