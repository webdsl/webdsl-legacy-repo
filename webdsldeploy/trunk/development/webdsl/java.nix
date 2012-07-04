{stdenv, fetchurl, unzip}:

stdenv.mkDerivation {
  name = "webdsl-java";
  src = fetchurl {
    url = http://hydra.nixos.org/build/2740829/download/1/webdsl-java.zip;
    sha256 = "d430eb5803bd08a63026e67d5b4314f8f34f00299c7b0346a44f07eff2a616fa";
  };
  buildInputs = [ unzip ];
  installPhase = ''
    ensureDir $out
    cp -av * $out
    chmod a+x $out/bin/*
  '';
}
