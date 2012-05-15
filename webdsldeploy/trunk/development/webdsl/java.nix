{stdenv, fetchurl, unzip}:

stdenv.mkDerivation {
  name = "webdsl-java";
  src = fetchurl {
    url = http://hydra.nixos.org/build/2613427/download/1/webdsl-java.zip;
    sha256 = "0f6yka3j0v8bsmg6zfhs7kvfg9w6jykjkxl4pipld0mhmjc6hh35";
  };
  buildInputs = [ unzip ];
  installPhase = ''
    ensureDir $out
    cp -av * $out
    chmod a+x $out/bin/*
  '';
}
