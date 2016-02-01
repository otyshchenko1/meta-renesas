DEPENDS = "jpeg \
           ${@base_contains('DISTRO_FEATURES', 'x11', 'virtual/libx11', '', d)}"
