{stdenv, fetchurl, pkgconfig, getopt, apacheAnt, aterm, sdf, strategoxt, javafront}:

rec {
  webdsl = stdenv.mkDerivation {
    name = "webdsl-9.7pre2895";
    src = fetchurl {
      url = http://hydra.nixos.org/build/61480/download/1/webdsl-9.7pre2895.tar.gz;
      sha256 = "1cvrjnjnhq7chw03938fi4hmhqdpii98dc0dg24kg4v3i0bwqd1w";
    };
    buildInputs = [ pkgconfig getopt aterm sdf strategoxt javafront ];
  };
  
  mkDerivation = attrs:
    assert attrs.databaseServer != null;
    assert attrs.databaseName != null;
    assert attrs.databaseUser != null;
    assert attrs.databasePassword != null;
    assert attrs.databaseMode == "update" || attrs.databaseMode == "create-drop";
  
    stdenv.mkDerivation ({      
      buildInputs = [ webdsl apacheAnt ];
      builder = ./webdsl-builder.sh;      
      inherit webdsl;
    } // attrs);
}
