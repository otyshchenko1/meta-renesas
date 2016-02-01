do_configure_prepend() {
    # The --enable-pc-files requires PKG_CONFIG_LIBDIR existed
    mkdir -p ${PKG_CONFIG_LIBDIR}
}


# see patch f90bb31ff494db4a778b1097c9265b9b2211868b for poky
inherit pkgconfig

# see patch f90bb31ff494db4a778b1097c9265b9b2211868b for poky
BUILD_CPPFLAGS_append_class-native = " -P"
BUILD_CPPFLAGS_append_class-nativesdk = " -P"

