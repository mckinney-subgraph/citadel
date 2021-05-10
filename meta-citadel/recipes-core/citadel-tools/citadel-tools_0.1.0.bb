SUMMARY = "${PN}"
HOMEPAGE = "http://github.com/subgraph/citadel"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM=""

inherit cargo systemd
#
# Update this when changes are pushed to github
#
SRCREV = "f665490a4d8bce26c2b70fba94731c60c581ddbd"

GIT_URI = "git://github.com/brl/citadel-tools.git;protocol=https"

# If Cargo.lock changes in citadel-tools, this needs to be updated.
# cargo bitbake does not support workspaces so as a workaround first
# copy the Cargo.lock file into one of the tool subdirectories. In
# that subdirectory run "cargo bitbake" and it will produce a bitbake
# recipe file with the correct set of dependencies for the Cargo.lock
# file.  Copy just the SRC_URI variable from that file here to update
# the dependency list.

SRC_URI += " \
crate://crates.io/addr2line/0.12.1 \
crate://crates.io/adler32/1.1.0 \
crate://crates.io/ansi_term/0.11.0 \
crate://crates.io/anyhow/1.0.33 \
crate://crates.io/arc-swap/0.4.7 \
crate://crates.io/array-macro/1.0.3 \
crate://crates.io/atk-sys/0.10.0 \
crate://crates.io/atk/0.9.0 \
crate://crates.io/atty/0.2.14 \
crate://crates.io/autocfg/1.0.0 \
crate://crates.io/backtrace/0.3.49 \
crate://crates.io/bincode/1.2.1 \
crate://crates.io/bitflags/1.2.1 \
crate://crates.io/block-buffer/0.9.0 \
crate://crates.io/block-cipher/0.7.1 \
crate://crates.io/block-padding/0.2.0 \
crate://crates.io/blowfish/0.5.0 \
crate://crates.io/byteorder/1.3.4 \
crate://crates.io/cairo-rs/0.9.1 \
crate://crates.io/cairo-sys-rs/0.10.0 \
crate://crates.io/cc/1.0.54 \
crate://crates.io/cfg-if/0.1.10 \
crate://crates.io/chrono/0.4.11 \
crate://crates.io/clap/2.33.1 \
crate://crates.io/cpuid-bool/0.1.2 \
crate://crates.io/crc32fast/1.2.0 \
crate://crates.io/crossbeam-channel/0.3.9 \
crate://crates.io/crossbeam-utils/0.6.6 \
crate://crates.io/crypto-mac/0.8.0 \
crate://crates.io/cursive/0.11.0 \
crate://crates.io/dbus/0.6.5 \
crate://crates.io/dbus/0.8.4 \
crate://crates.io/digest/0.9.0 \
crate://crates.io/either/1.6.1 \
crate://crates.io/enum-map-derive/0.4.3 \
crate://crates.io/enum-map-internals/0.1.3 \
crate://crates.io/enum-map/0.5.0 \
crate://crates.io/enumset/0.3.19 \
crate://crates.io/enumset_derive/0.3.2 \
crate://crates.io/failure/0.1.8 \
crate://crates.io/failure_derive/0.1.8 \
crate://crates.io/filetime/0.2.10 \
crate://crates.io/futures/0.3.6 \
crate://crates.io/futures-channel/0.3.6 \
crate://crates.io/futures-core/0.3.6 \
crate://crates.io/futures-executor/0.3.6 \
crate://crates.io/futures-io/0.3.6 \
crate://crates.io/futures-macro/0.3.6 \
crate://crates.io/futures-sink/0.3.6 \
crate://crates.io/futures-task/0.3.6 \
crate://crates.io/futures-util/0.3.6 \
crate://crates.io/fuzzy-matcher/0.3.7 \
crate://crates.io/generic-array/0.14.4 \
crate://crates.io/getrandom/0.1.15 \
crate://crates.io/gdk-pixbuf-sys/0.10.0 \
crate://crates.io/gdk-pixbuf/0.9.0 \
crate://crates.io/gdk-sys/0.10.0 \
crate://crates.io/gdk/0.13.2 \
crate://crates.io/gimli/0.21.0 \
crate://crates.io/gio-sys/0.10.1 \
crate://crates.io/gio/0.9.1 \
crate://crates.io/glib-sys/0.10.1 \
crate://crates.io/glib/0.10.2 \
crate://crates.io/glib-macros/0.10.1 \
crate://crates.io/gobject-sys/0.10.0 \
crate://crates.io/gtk-sys/0.10.0 \
crate://crates.io/gtk/0.9.2 \
crate://crates.io/hermit-abi/0.1.14 \
crate://crates.io/heck/0.3.1 \
crate://crates.io/hex/0.4.2 \
crate://crates.io/hmac/0.8.1 \
crate://crates.io/inotify-sys/0.1.3 \
crate://crates.io/inotify/0.8.3 \
crate://crates.io/itertools/0.9.0 \
crate://crates.io/lazy_static/1.4.0 \
crate://crates.io/libc/0.2.71 \
crate://crates.io/libdbus-sys/0.2.1 \
crate://crates.io/libflate/0.1.27 \
crate://crates.io/libsodium-sys/0.2.5 \
crate://crates.io/log/0.4.8 \
crate://crates.io/md-5/0.9.1 \
crate://crates.io/memchr/2.3.3 \
crate://crates.io/miniz_oxide/0.3.7 \
crate://crates.io/nix/0.17.0 \
crate://crates.io/num-complex/0.2.4 \
crate://crates.io/num-integer/0.1.43 \
crate://crates.io/num-iter/0.1.41 \
crate://crates.io/num-rational/0.2.4 \
crate://crates.io/num-traits/0.2.12 \
crate://crates.io/num/0.2.1 \
crate://crates.io/numtoa/0.1.0 \
crate://crates.io/object/0.20.0 \
crate://crates.io/once_cell/1.4.0 \
crate://crates.io/opaque-debug/0.2.3 \
crate://crates.io/opaque-debug/0.3.0 \
crate://crates.io/owning_ref/0.4.1 \
crate://crates.io/pango-sys/0.10.0 \
crate://crates.io/pango/0.9.1 \
crate://crates.io/pin-project-internal/0.4.22 \
crate://crates.io/pin-project/0.4.22 \
crate://crates.io/pin-utils/0.1.0 \
crate://crates.io/pkg-config/0.3.17 \
crate://crates.io/ppv-lite86/0.2.9 \
crate://crates.io/proc-macro-crate/0.1.5 \
crate://crates.io/proc-macro-error/1.0.4 \
crate://crates.io/proc-macro-error-attr/1.0.4 \
crate://crates.io/proc-macro-hack/0.5.16 \
crate://crates.io/proc-macro-nested/0.1.6 \
crate://crates.io/proc-macro2/0.4.30 \
crate://crates.io/proc-macro2/1.0.18 \
crate://crates.io/pwhash/0.3.1 \
crate://crates.io/quote/0.6.13 \
crate://crates.io/quote/1.0.7 \
crate://crates.io/rand/0.7.3 \
crate://crates.io/rand_chacha/0.2.2 \
crate://crates.io/rand_core/0.5.1 \
crate://crates.io/rand_hc/0.2.0 \
crate://crates.io/redox_syscall/0.1.56 \
crate://crates.io/redox_termios/0.1.1 \
crate://crates.io/rle-decode-fast/1.0.1 \
crate://crates.io/rpassword/4.0.5 \
crate://crates.io/rustc-demangle/0.1.16 \
crate://crates.io/same-file/1.0.6 \
crate://crates.io/serde/1.0.112 \
crate://crates.io/serde_derive/1.0.112 \
crate://crates.io/sha-1/0.9.1 \
crate://crates.io/sha2/0.9.1 \
crate://crates.io/signal-hook-registry/1.2.0 \
crate://crates.io/signal-hook/0.1.16 \
crate://crates.io/slab/0.4.2 \
crate://crates.io/sodiumoxide/0.2.5 \
crate://crates.io/stable_deref_trait/1.1.1 \
crate://crates.io/strsim/0.8.0 \
crate://crates.io/strum/0.18.0 \
crate://crates.io/strum_macros/0.18.0 \
crate://crates.io/subtle/2.3.0 \
crate://crates.io/syn/0.15.44 \
crate://crates.io/syn/1.0.31 \
crate://crates.io/synstructure/0.12.4 \
crate://crates.io/system-deps/1.3.2 \
crate://crates.io/take_mut/0.2.2 \
crate://crates.io/tar/0.4.29 \
crate://crates.io/termion/1.5.5 \
crate://crates.io/textwrap/0.11.0 \
crate://crates.io/thread_local/1.0.1 \
crate://crates.io/thiserror/1.0.21 \
crate://crates.io/thiserror-impl/1.0.21 \
crate://crates.io/time/0.1.43 \
crate://crates.io/toml/0.4.10 \
crate://crates.io/toml/0.5.6 \
crate://crates.io/typenum/1.12.0 \
crate://crates.io/unicode-segmentation/1.6.0 \
crate://crates.io/unicode-width/0.1.7 \
crate://crates.io/unicode-xid/0.1.0 \
crate://crates.io/unicode-xid/0.2.0 \
crate://crates.io/vcpkg/0.2.10 \
crate://crates.io/vec_map/0.8.2 \
crate://crates.io/version-compare/0.0.10 \
crate://crates.io/version_check/0.9.2 \
crate://crates.io/void/1.0.2 \
crate://crates.io/walkdir/2.3.1 \
crate://crates.io/wasi/0.9.0+wasi-snapshot-preview1 \
crate://crates.io/winapi-i686-pc-windows-gnu/0.4.0 \
crate://crates.io/winapi-util/0.1.5 \
crate://crates.io/winapi-x86_64-pc-windows-gnu/0.4.0 \
crate://crates.io/winapi/0.3.9 \
crate://crates.io/xattr/0.2.2 \
crate://crates.io/xi-unicode/0.1.0 \
"

export SODIUM_USE_PKG_CONFIG = "1"

DEPENDS = "libsodium openssl dbus gtk+3 glib-2.0"
BBCLASSEXTEND = "native"
PACKAGES =+ "${PN}-realms ${PN}-tools ${PN}-mkimage ${PN}-boot"

FILES_${PN}-realms = "${bindir}/realms"
FILES_${PN}-mkimage = "${bindir}/citadel-mkimage"
FILES_${PN}-boot = "${libexecdir}/citadel-boot"

FILES_${PN} = "\
    ${libexecdir}/citadel-tool \
    ${libexecdir}/citadel-boot \
    ${libexecdir}/citadel-run \
    ${libexecdir}/citadel-install \
    ${libexecdir}/citadel-install-backend \
    ${libexecdir}/citadel-desktop-sync \
    ${libexecdir}/citadel-realmsd \
    ${libexecdir}/citadel-realms-ui \
    ${libexecdir}/citadel-installer-ui \
    ${bindir}/citadel-image \
    ${bindir}/citadel-realmfs \
    ${bindir}/citadel-update \
    ${systemd_system_unitdir} \
    ${sysconfdir}/dbus-1/system.d \
"

SYSTEMD_SERVICE_${PN} = "citadel-current-watcher.path citadel-realmsd.service citadel-boot-automount.service"

TARGET_BIN = "${B}/target/${CARGO_TARGET_SUBDIR}"

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${libexecdir}
    install -d ${D}${systemd_system_unitdir}

    # Services desktop sync
    install -m 644 ${S}/systemd/citadel-desktop-watcher.path ${D}${systemd_system_unitdir}
    install -m 644 ${S}/systemd/citadel-desktop-watcher.service ${D}${systemd_system_unitdir}
    install -m 644 ${S}/systemd/citadel-current-watcher.path ${D}${systemd_system_unitdir}
    install -m 644 ${S}/systemd/citadel-current-watcher.service ${D}${systemd_system_unitdir}

    # Unit to run: citadel-boot boot-automount
    install -m 644 ${S}/systemd/citadel-boot-automount.service ${D}${systemd_system_unitdir}

    # realmsd
    install -m 644 ${S}/data/citadel-realmsd.service ${D}${systemd_system_unitdir}
    install -m 755 -T ${TARGET_BIN}/realmsd ${D}${libexecdir}/citadel-realmsd
    install -d ${D}${sysconfdir}/dbus-1/system.d
    install -m 644 ${S}/data/com.subgraph.realms.Manager.conf ${D}${sysconfdir}/dbus-1/system.d

    # citadel-realms-ui
    install -m 755 ${TARGET_BIN}/citadel-realms-ui ${D}${libexecdir}

    # citadel-installer-ui
    install -m 755 ${TARGET_BIN}/citadel-installer-ui ${D}${libexecdir}
    install -m 644 ${S}/data/com.subgraph.installer.Manager.conf ${D}${sysconfdir}/dbus-1/system.d

    # /usr/libexec/citadel-tool
    install -m 755 ${TARGET_BIN}/citadel-tool ${D}${libexecdir}

    # citadel-realms as /usr/bin/realms
    install -m 755 -T ${TARGET_BIN}/citadel-realms ${D}${bindir}/realms

    ln ${D}${libexecdir}/citadel-tool ${D}${libexecdir}/citadel-boot
    ln ${D}${libexecdir}/citadel-tool ${D}${libexecdir}/citadel-install
    ln ${D}${libexecdir}/citadel-tool ${D}${libexecdir}/citadel-install-backend
    ln ${D}${libexecdir}/citadel-tool ${D}${libexecdir}/citadel-desktop-sync
    ln ${D}${libexecdir}/citadel-tool ${D}${libexecdir}/citadel-run
    ln ${D}${libexecdir}/citadel-tool ${D}${bindir}/citadel-image
    ln ${D}${libexecdir}/citadel-tool ${D}${bindir}/citadel-mkimage
    ln ${D}${libexecdir}/citadel-tool ${D}${bindir}/citadel-realmfs
    ln ${D}${libexecdir}/citadel-tool ${D}${bindir}/citadel-update
}

#
# To make development more convenient citadel-tools recipes support
# building from a checked out tree on the filesystem. If the variable
# CITADEL_TOOLS_PATH is set (preferably in build/conf/local.conf) then
# bitbake will not check out the source files from git but instead will
# copy the directory this variable contains.
#

#
#
# By default:
#
#     S = "${WORKDIR}/git"
#
# if CITADEL_TOOLS_PATH is set:
#
#     S = "${WORKDIR}${CITADEL_TOOLS_PATH}"
#
S = "${WORKDIR}${@source_path(d)}"

#
# By default:
#
#     SRC_URI += "${GIT_URI}"
#
# If CITADEL_TOOLS_PATH is set:
#
#     SRC_URI += "file://${CITADEL_TOOLS_PATH}"
#
SRC_URI += "${@source_uri(d)}"

def source_path(d):
    tools_path = d.getVar("CITADEL_TOOLS_PATH")

    if tools_path:
        return tools_path
    else:
        return "/git"

def source_uri(d):
    tools_path = d.getVar("CITADEL_TOOLS_PATH")
    if tools_path:
        return "file://" + tools_path
    else:
        return d.getVar("GIT_URI")

# Set debug build if CITADEL_TOOLS_PATH is set for faster builds
DEBUG_BUILD = "${@debug_build(d)}"
def debug_build(d):
    tools_path = d.getVar("CITADEL_TOOLS_PATH")
    if tools_path:
        return "1"
    else:
        return "0"

#do_fetch[file-checksums] = ""
