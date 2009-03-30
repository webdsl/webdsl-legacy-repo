{stdenv, fetchurl, pkgconfig, getopt, aterm, sdf2_bundle}:

stdenv.mkDerivation {
  name = "strategoxt-0.17pre18269";
  src = fetchurl {
    url = http://releases.strategoxt.org/strategoxt/strategoxt-0.17pre18269-a0f0wy0j/strategoxt-0.17pre18269.tar.gz;
    sha256 = "7c51c2452bd45f34cd480b6b3cbaac50e0fc53fbb1a884d97cf4e2c2b5330577";
  };
  buildInputs = [ pkgconfig getopt aterm sdf2_bundle ];
}
