This is vile and non-repeatable, but it produces a working chef client on AIX.

This is not an attempt to do things correctly and integrate AIX into the omnibus
toolchain.  It is the minimal set of deltas required to make things build.  The
resulting stack is acceptably self-contained, with no non-base-system
dependencies that I can see.

* Start with system with "IBM AIX Toolbox for Linux Applications", gcc being the
  key requirement.

* Get a system ruby and rubygems on the system somehow, install ohai.  I used an
  in-house built 1.8.7 rpm we run puppet against.

* I installed the following to a custom build prefix:
** bison-2.3
** flex-2.5.4
** m4-1.4.5
** make-3.81
** texinfo-4.8

* Build, with build prefix first in the path.

* Ruby silently fails to build openssl extension as extconf tests don't link
  against zlib.  Add -lz to $libs in extconf.rb, build and install.  I didn't
  patch the source tree with this as there's probably some appropriate flag to
  pass to configure to make this work directly.

* Copy libgcc_s.a into /opt/opscode/embedded/lib.

* Post-build, install sigar gem in embedded ruby.  Sigar needs a patch to build
  on AIX:

diff -Nrub sigar-0.7.0/src/os/aix/aix_sigar.c sigar-0.7.0.mod/src/os/aix/aix_sigar.c
--- sigar-0.7.0/src/os/aix/aix_sigar.c  1969-12-31 19:00:00.000000000 -0500
+++ sigar-0.7.0.mod/src/os/aix/aix_sigar.c      2012-01-03 19:32:54.000000000 -0500
@@ -33,6 +33,7 @@
 #include <pthread.h>
 #include <stdio.h>
 #include <utmp.h>
+#include <sys/protosw.h>
 #include <libperfstat.h>
 #include <pthread.h>

Chris Buben
cbuben@gmail.com

# vim: ai et sw=4 ts=4 tw=80
