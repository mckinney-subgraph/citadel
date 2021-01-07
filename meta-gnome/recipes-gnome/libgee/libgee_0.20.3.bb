SUMMARY = "A collection library providing GObject based interfaces and classes for commonly used data structures"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

SRC_URI[archive.md5sum] = "e574b3952b93d219b5ec7c74c5892c33"
SRC_URI[archive.sha256sum] = "d0b5edefc88cbca5f1709d19fa62aef490922c6577a14ac4e7b085507911a5de"

DEPENDS = "glib-2.0"

PACKAGES += "${PN}-vala"
FILES_${PN}-vala = "${datadir}/vala/vapi"

inherit gnomebase pkgconfig autotools

EXTRA_OECONF = ""

