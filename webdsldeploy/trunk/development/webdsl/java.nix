{stdenv, fetchurl, unzip}:

stdenv.mkDerivation {
  name = "webdsl-java";
  src = fetchurl {
    url = http://hydra.nixos.org/build/2515402/download/1/webdsl-java.zip;
    sha256 = "1a919a1585d9cb0ab97115b1f947e805bbe789e55e5c1349b76cd42128bf07fd";
  };
  buildInputs = [ unzip ];
  installPhase = ''
    ensureDir $out
    cp -av * $out
    chmod a+x $out/bin/*
  '';
}
