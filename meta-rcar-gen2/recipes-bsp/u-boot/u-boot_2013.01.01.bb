require u-boot.inc

# This is needs to be validated among supported BSP's before we can
# make it default
DEFAULT_PREFERENCE = "-1"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

PV = "v2013.01.01+git${SRCPV}"

SRCREV = "7b8ebcc25719f85ae4bce2b6b997baf8070e6d35"
SRC_URI = "git://git.denx.de/u-boot-sh.git;branch=renesas/bsp/rcar-gen2-1.9.6;protocol=git"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(alt|gose|koelsch|lager|porter|silk|stout)"

SRC_URI_append_lcb = " \
    file://0001-arm-rmobile-Add-SILK-board-support.patch \
    file://0002-arm-rmobile-Add-Porter-board-support.patch \
    file://0003-serial-serial-sh-SCIF-internal-clock-source-support.patch \
    file://0004-ARM-cpu-Add-ARMv7-barrier-operations-support.patch \
    file://0007-gpio-sh-pfc-fix-gpio-input-read.patch \
    file://0008-serial-serial-sh-SCIFA-interface-for-R-Car-Gen2-SoCs.patch \
    file://0009-arm-rmobile-Add-r8a7790-stout-board-support.patch \
    file://0010-Support-R-Car-Gen2-USB-PHY.patch \
    file://0011-enable-rmobile-usb-phy-on-silk.patch \
    file://0001-ARM-prepare-armv7.h-to-be-included-from-assembly-sou.patch \
    file://0002-ARM-add-secure-monitor-handler-to-switch-to-non-secu.patch \
    file://0003-ARM-add-assembly-routine-to-switch-to-non-secure-sta.patch \
    file://0004-ARM-add-C-function-to-switch-to-non-secure-state.patch \
    file://0005-ARM-trigger-non-secure-state-switch-during-bootm-exe.patch \
    file://0006-ARM-add-SMP-support-for-non-secure-switch.patch \
    file://0007-ARM-extend-non-secure-switch-to-also-go-into-HYP-mod.patch \
    file://0008-ARM-align-MVBAR-on-32-byte-boundary.patch \
    file://0009-ARM-HYP-non-sec-move-switch-to-non-sec-to-the-last-b.patch \
    file://0010-ARM-HYP-non-sec-add-a-barrier-after-setting-SCR.NS-1.patch \
    file://0011-ARM-non-sec-reset-CNTVOFF-to-zero.patch \
    file://0012-ARM-add-missing-HYP-mode-constant.patch \
    file://0013-ARM-HYP-non-sec-remove-MIDR-check-to-validate-CBAR.patch \
    file://0014-ARM-HYP-non-sec-allow-relocation-to-secure-RAM.patch \
    file://0015-arm-rmobile-Add-support-of-starting-CPUs-in-Hypervis.patch \
    file://0016-arm-rmobile-Add-timer-initialization.patch \
    file://0017-ARM-HYP-non-sec-drop-arch-time-initialization.patch \
    file://0018-Copy-lager-stuff-for-switching-A15-cores-to-HYP-mode.patch \
"
