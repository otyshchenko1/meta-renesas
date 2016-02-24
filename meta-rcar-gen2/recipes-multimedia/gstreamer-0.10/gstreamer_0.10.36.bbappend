
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://gstreamer-change-priv_gst_parse_yylex-arguments.patch"

