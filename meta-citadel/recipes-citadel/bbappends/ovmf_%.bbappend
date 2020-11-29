
FILESEXTRAPATHS_prepend := "${THISDIR}/ovmf:"

# Fixes build failure when host Python updated to 3.9

SRC_URI += "\
    file://python3.9-fix-ucs2-lookup.patch \
    file://python3.9-workaround-array-tostring-removal.patch \
"
