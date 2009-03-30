{stdenv, fetchurl, pkgconfig, getopt, apacheAnt, aterm, sdf2_bundle, strategoxt, javafront}:

rec {
  webdsl = stdenv.mkDerivation {
    name = "webdsl-8.8pre2590";
    src = fetchurl {
      url = http://releases.strategoxt.org/webdsl/webdsl-8.8pre25902590-pfd8j82s/webdsl-8.8pre2590.tar.gz;
      sha256 = "1607d60f3e6fd296fb07ade92a7d06e91eb64622b2b573b81221f918bc5de5d8";
    };
    buildInputs = [ pkgconfig getopt aterm sdf2_bundle strategoxt javafront ];
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
