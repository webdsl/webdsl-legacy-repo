{stdenv, fetchurl}:

stdenv.mkDerivation {
  name = "aterm-2.5";
  src = fetchurl {
    url = ftp://ftp.stratego-language.org/pub/stratego/StrategoXT/strategoxt-0.17/aterm-2.5.tar.gz;
    sha256 = "164di6my7iy2ybfl46fa2hw6fn6vgqfn2jrpy5mkpcbb4cc5pg4v";
  };
}
