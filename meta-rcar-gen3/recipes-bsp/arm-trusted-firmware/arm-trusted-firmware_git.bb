DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.rst;md5=33065335ea03d977d0569f270b39603e"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy
require include/multimedia-control.inc
require include/arm-trusted-firmware-control.inc

S = "${WORKDIR}/git"

BRANCH = "rcar_gen3"
SRC_URI = "git://github.com/renesas-rcar/arm-trusted-firmware.git;branch=${BRANCH}"
SRCREV = "15dba6bb5868bdfad723bb727684b37b48643fec"

PV = "v1.4+renesas+git${SRCPV}"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"
PLATFORM = "rcar"
H3_IPL_OPTION = "${@get_ipl_config_opt(d)}"
ATFW_OPT_LOSSY = "${@base_conditional("USE_MULTIMEDIA", "1", "RCAR_LOSSY_ENABLE=1", "", d)}"
ATFW_OPT_r8a7795 = "LSI=H3 ${H3_IPL_OPTION} ${ATFW_OPT_LOSSY}"
ATFW_OPT_r8a7796 = "LSI=M3 RCAR_DRAM_SPLIT=2 ${ATFW_OPT_LOSSY}"
ATFW_OPT_r8a77965 = "LSI=M3N ${ATFW_OPT_LOSSY}"
ATFW_OPT_r8a77990 = "LSI=E3 RCAR_SA0_SIZE=0 RCAR_AVS_SETTING_ENABLE=0"
ATFW_OPT_append_ulcb = " RCAR_GEN3_ULCB=1 PMIC_LEVEL_MODE=0"

# Start cores in EL2 mode
ATFW_OPT_append = ' RCAR_BL33_EXECUTION_EL=BL33_EL2'

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_compile() {
    oe_runmake bl2 bl31 dummytool PLAT=${PLATFORM} ${ATFW_OPT}
}

# do_install() nothing
do_install[noexec] = "1"

do_deploy() {
    # Create deploy folder
    install -d ${DEPLOYDIR}

    # Copy IPL to deploy folder
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2/bl2.elf ${DEPLOYDIR}/bl2-${MACHINE}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.bin ${DEPLOYDIR}/bl2-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.srec ${DEPLOYDIR}/bl2-${MACHINE}.srec
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31/bl31.elf ${DEPLOYDIR}/bl31-${MACHINE}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.bin ${DEPLOYDIR}/bl31-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.srec ${DEPLOYDIR}/bl31-${MACHINE}.srec
    install -m 0644 ${S}/tools/dummy_create/bootparam_sa0.srec ${DEPLOYDIR}/bootparam_sa0.srec
    install -m 0644 ${S}/tools/dummy_create/cert_header_sa6.srec ${DEPLOYDIR}/cert_header_sa6.srec
}
addtask deploy before do_build after do_compile
