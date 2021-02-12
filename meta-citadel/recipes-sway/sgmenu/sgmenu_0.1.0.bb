SUMMARY = "${PN}"
HOMEPAGE = "http://github.com/subgraph/citadel"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM=""

inherit cargo systemd
#
# Update this when changes are pushed to github
#
SRCREV = "32b4b0886a2a1872b4b7a2d36236daf0b94578d6"

SRC_URI = "git://github.com/subgraph/sgmenu.git;protocol=https"

S = "${WORKDIR}/git"

# If Cargo.lock changes in citadel-tools, this needs to be updated.
# cargo bitbake does not support workspaces so as a workaround first
# copy the Cargo.lock file into one of the tool subdirectories. In
# that subdirectory run "cargo bitbake" and it will produce a bitbake
# recipe file with the correct set of dependencies for the Cargo.lock
# file.  Copy just the SRC_URI variable from that file here to update
# the dependency list.

SRC_URI += " \
crate://crates.io/aho-corasick/0.7.13 \
crate://crates.io/anyhow/1.0.31 \
crate://crates.io/atk/0.9.0 \
crate://crates.io/atk-sys/0.10.0 \
crate://crates.io/atty/0.2.14 \
crate://crates.io/bitflags/1.2.1 \
crate://crates.io/cairo-rs/0.9.1 \
crate://crates.io/cairo-sys-rs/0.10.0 \
crate://crates.io/cc/1.0.58 \
crate://crates.io/cfg-if/0.1.10 \
crate://crates.io/either/1.5.3 \
crate://crates.io/env_logger/0.7.1 \
crate://crates.io/futures/0.3.5 \
crate://crates.io/futures-channel/0.3.5 \
crate://crates.io/futures-core/0.3.5 \
crate://crates.io/futures-executor/0.3.5 \
crate://crates.io/futures-io/0.3.5 \
crate://crates.io/futures-macro/0.3.5 \
crate://crates.io/futures-sink/0.3.5 \
crate://crates.io/futures-task/0.3.5 \
crate://crates.io/futures-util/0.3.5 \
crate://crates.io/gdk/0.13.2 \
crate://crates.io/gdk-pixbuf/0.9.0 \
crate://crates.io/gdk-pixbuf-sys/0.10.0 \
crate://crates.io/gdk-sys/0.10.0 \
crate://crates.io/gio/0.9.1 \
crate://crates.io/gio-sys/0.10.1 \
crate://crates.io/glib/0.10.3 \
crate://crates.io/glib-macros/0.10.1 \
crate://crates.io/glib-sys/0.10.1 \
crate://crates.io/gobject-sys/0.10.0 \
crate://crates.io/gtk/0.9.2 \
crate://crates.io/gtk-sys/0.10.0 \
crate://crates.io/heck/0.3.1 \
crate://crates.io/hermit-abi/0.1.15 \
crate://crates.io/humantime/1.3.0 \
crate://crates.io/itertools/0.9.0 \
crate://crates.io/lazy_static/1.4.0 \
crate://crates.io/libc/0.2.72 \
crate://crates.io/log/0.4.11 \
crate://crates.io/memchr/2.3.3 \
crate://crates.io/once_cell/1.4.0 \
crate://crates.io/pango/0.9.1 \
crate://crates.io/pango-sys/0.10.0 \
crate://crates.io/pin-project/0.4.22 \
crate://crates.io/pin-project-internal/0.4.22 \
crate://crates.io/pin-utils/0.1.0 \
crate://crates.io/pkg-config/0.3.17 \
crate://crates.io/proc-macro2/1.0.18 \
crate://crates.io/proc-macro-crate/0.1.5 \
crate://crates.io/proc-macro-error/1.0.3 \
crate://crates.io/proc-macro-error-attr/1.0.3 \
crate://crates.io/proc-macro-hack/0.5.16 \
crate://crates.io/proc-macro-nested/0.1.6 \
crate://crates.io/quick-error/1.2.3 \
crate://crates.io/quote/1.0.7 \
crate://crates.io/regex/1.3.9 \
crate://crates.io/regex-syntax/0.6.18 \
crate://crates.io/serde/1.0.114 \
crate://crates.io/slab/0.4.2 \
crate://crates.io/strum/0.18.0 \
crate://crates.io/strum_macros/0.18.0 \
crate://crates.io/syn/1.0.33 \
crate://crates.io/syn-mid/0.5.0 \
crate://crates.io/system-deps/1.3.2 \
crate://crates.io/termcolor/1.1.0 \
crate://crates.io/thiserror/1.0.20 \
crate://crates.io/thiserror-impl/1.0.20 \
crate://crates.io/thread_local/1.0.1 \
crate://crates.io/toml/0.5.6 \
crate://crates.io/unicode-segmentation/1.6.0 \
crate://crates.io/unicode-xid/0.2.1 \
crate://crates.io/version_check/0.9.2 \
crate://crates.io/version-compare/0.0.10 \
crate://crates.io/winapi/0.3.9 \
crate://crates.io/winapi-util/0.1.5 \
crate://crates.io/winapi-i686-pc-windows-gnu/0.4.0 \
crate://crates.io/winapi-x86_64-pc-windows-gnu/0.4.0 \
"

export SODIUM_USE_PKG_CONFIG = "1"

DEPENDS = "gtk-layer-shell gtk+3 glib-2.0"
BBCLASSEXTEND = "native"
