do_install_append() {
	echo "" >> ${D}${sysconfdir}/inittab
	echo "X0:12345:respawn:/sbin/getty 38400 hvc0" >> ${D}${sysconfdir}/inittab
}

