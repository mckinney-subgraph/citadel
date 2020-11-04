# Remove this dependency so it doesn't get dragged in with gtk.  These icons are in citadel-extra-image now
GTKBASE_RRECOMMENDS_remove = "adwaita-icon-theme-symbolic"

FILESEXTRAPATHS_prepend := "${THISDIR}/gtk+3:"

SRC_URI += "file://gtk-update-icon-cache-reproducibility-fix.patch"
