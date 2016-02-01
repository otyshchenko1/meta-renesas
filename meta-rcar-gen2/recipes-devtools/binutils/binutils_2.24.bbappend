FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# make compilation with GCC 5 possible
SRC_URI_append = " file://Fix-tc-i386.c-Werror-logical-not-parentheses-error.patch"

