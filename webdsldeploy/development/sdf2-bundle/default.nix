{stdenv, fetchurl, aterm, pkgconfig}:

stdenv.mkDerivation {
  name = "sdf2-bundle-2.4pre212034";
  src = fetchurl {
    url = http://releases.strategoxt.org/strategoxt-0.17/sdf2-bundle/sdf2-bundle-2.4pre212034-37nm9z7p/sdf2-bundle-2.4.tar.gz;
    sha256 = "2ec83151173378f48a3326e905d11049d094bf9f0c7cff781bc2fce0f3afbc11";
  };
  buildInputs = [ pkgconfig aterm ];  
}
