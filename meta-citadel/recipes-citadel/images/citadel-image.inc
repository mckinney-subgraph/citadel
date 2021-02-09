
CITADEL_IMAGE_CHANNEL = "dev"

CITADEL_IMAGE_VERSION_rootfs = "1"
CITADEL_IMAGE_VERSION_extra = "1"
CITADEL_IMAGE_VERSION_kernel = "1"

CITADEL_KERNEL_VERSION = "5.10.11"

CITADEL_KERNEL_CONFIG = "${COREBASE}/../meta-citadel/recipes-kernel/citadel-kernel/files/defconfig"

generate_kernel_id() {
    sha256sum ${CITADEL_KERNEL_CONFIG} | cut -d' ' -f1
}
