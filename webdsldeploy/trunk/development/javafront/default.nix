{stdenv, fetchurl, pkgconfig, aterm, strategoxt}:

stdenv.mkDerivation {
  name = "java-front-0.9pre1823618236";
  src = fetchurl {
    url = http://releases.strategoxt.org/java-front/java-front-0.9pre1823618236-frb8zh7m/java-front-0.9pre18236.tar.gz;
    sha256 = "93d2919cfbda41a96a944f71ae57704ad1f0efcc0c1084b501a4536f82e25387";
  };
  buildInputs = [ pkgconfig aterm strategoxt ];
}
