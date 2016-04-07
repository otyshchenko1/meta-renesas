FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

S = "${WORKDIR}"

SRC_URI_append = " file://adas-switch-init"

do_install_append() {
    install -d ${D}/${sysconfdir}/init.d
    if [ "${@base_contains('MACHINE_FEATURES', 'stout-had', 'stout-had', '', d)}" = "stout-had" ]; then
        install -m 755 ${S}/adas-switch-init ${D}/${sysconfdir}/init.d/adas-switch-init.sh
        update-rc.d -r ${D} adas-switch-init.sh start 5 S .
    fi
}
