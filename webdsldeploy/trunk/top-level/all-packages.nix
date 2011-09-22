{ pkgs ? import (builtins.getEnv "NIXPKGS_ALL") {}
, system ? builtins.currentSystem
}:

rec {    
  webdsl = import ../development/webdsl {
    inherit (pkgs) stdenv pkgconfig apacheAnt perl autoconf automake libtool;
    inherit (pkgs.strategoPackages018) aterm sdf strategoxt javafront;
  };
  
  webdsl_java = import ../development/webdsl/java.nix {
    inherit (pkgs) stdenv fetchurl unzip;
  };
  
  webdslBuild = import ../development/webdsl/webdslbuild.nix {
    inherit (pkgs) stdenv apacheAnt;
    inherit webdsl;
  };
  
  webdslBuildJava = import ../development/webdsl/webdslbuild.nix {
    inherit (pkgs) stdenv apacheAnt;
    webdsl = webdsl_java;
  };
}
