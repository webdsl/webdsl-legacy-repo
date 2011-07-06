{stdenv, aterm, sdf, strategoxt, javafront, perl, autoconf, automake, libtool, pkgconfig, apacheAnt}:

stdenv.mkDerivation {
  name = "webdsl-trunk";
  src = /home/sander/webdsls;
  configureFlags = "--enable-web-check=no";
  preConfigure = "ulimit -s ${if stdenv.isDarwin then "64000" else "unlimited"}; ./bootstrap";
  buildInputs = [ aterm sdf strategoxt javafront perl autoconf automake libtool pkgconfig apacheAnt ];
}
