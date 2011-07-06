{ pkgs ? import (builtins.getEnv "NIXPKGS_ALL") {}
, system ? builtins.currentSystem
}:

rec {    
  webdsl = import ../development/webdsl {
    inherit (pkgs) stdenv pkgconfig apacheAnt perl autoconf automake libtool;
    inherit (pkgs.strategoPackages018) aterm sdf strategoxt javafront;
  };
  
  webdslbuild = import ../development/webdsl/webdslbuild.nix {
    inherit (pkgs) stdenv apacheAnt;
    inherit webdsl;
  };
}
