{stdenv, fetchurl, unzip}:

stdenv.mkDerivation {
  name = "webdsl-java";
  src = fetchurl {
    url = http://hydra.nixos.org/build/1822081/download/1/webdsl-java.zip;
    sha256 = "1avgkgrib16mlwvi3vnkw2cwyy25a3fbv3ybp2125c9ydczznwrq";
  };
  buildInputs = [ unzip ];
  installPhase = ''
    ensureDir $out
    cp -av * $out
  '';
}
