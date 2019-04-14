LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b53d9ec16b9125995437ac9efab1b450"

SRC_URI = "https://github.com/swaywm/swaylock/archive/${PV}.tar.gz \
		   "

SRC_URI[md5sum] = "60bb92e6ae639daef955f157e6128efe"
SRC_URI[sha256sum] = "3bf4143136e688af2f31d10c0be26d14e371f31bfceec1d98db88abfe0f7a94c"

DEPENDS = "wlroots libxkbcommon wayland wayland-native libpam gdk-pixbuf cairo"

inherit meson 

EXTRA_OEMESON += "-Dbash-completions=false -Dfish-completions=false -Dzsh-completions=false" 

