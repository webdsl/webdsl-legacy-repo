{stdenv, fetchurl, unzip}:

stdenv.mkDerivation {
  name = "webdsl-java";
  src = fetchurl {
    url = http://hydra.nixos.org/build/1382987/download/1/webdsl-java.zip;
    sha256 = "1jp2jdvncg3z76h6iiqlxc7m97fqksb0dr2g9q4n7lw99k6fgiir";
  };
  buildInputs = [ unzip ];
  installPhase = ''
    ensureDir $out
    cp -av * $out
  '';
}
