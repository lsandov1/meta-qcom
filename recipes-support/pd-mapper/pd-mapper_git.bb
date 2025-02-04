SUMMARY = "Qualcomm pd-mapper application"
HOMEPAGE = "https://github.com/andersson/pd-mapper.git"
SECTION = "devel"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c5d4ab97bca4e843c5afdbf78aa5fdee"

DEPENDS = "qrtr"

inherit systemd

SRCREV = "d7fe25fa6eff2e62cf264544adee9e8ca830dc78"
SRC_URI = "git://github.com/andersson/${BPN}.git;branch=master;protocol=https \
"

PV = "0.0+${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "prefix=${prefix} bindir=${bindir} libdir=${libdir} includedir=${includedir} LDFLAGS='${LDFLAGS} -Wl,-lqrtr' CFLAGS='${CFLAGS}'"

do_install () {
    oe_runmake install DESTDIR=${D} prefix=${prefix} servicedir=${systemd_unitdir}/system
}

SYSTEMD_SERVICE:${PN} = "pd-mapper.service"
RDEPENDS:${PN} += "qrtr"
