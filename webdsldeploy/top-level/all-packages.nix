let pkgs = import (builtins.getEnv "NIXPKGS_ALL") { }; in
with pkgs;

rec {
#### Tools
  aterm = import ../libraries/aterm {
    inherit stdenv fetchurl;
  };
    
  sdf2_bundle = import ../development/sdf2-bundle {
    inherit stdenv fetchurl pkgconfig;
    inherit aterm;
  };
    
  strategoxt = import ../development/strategoxt {
    inherit stdenv fetchurl pkgconfig getopt;
    inherit aterm sdf2_bundle;
  };
    
  javafront = import ../development/javafront {
    inherit stdenv fetchurl pkgconfig;
    inherit aterm strategoxt;
  }; 
    
  webdsl = import ../development/webdsl {
    inherit stdenv fetchurl pkgconfig getopt apacheAnt;
    inherit aterm sdf2_bundle strategoxt javafront;
  };

#### WebDSL applications
  associations = import ../webdslapps/associations {
    inherit webdsl fetchsvn;
  };

  researchr = import ../webdslapps/researchr {
    inherit webdsl fetchsvn;
  };
  
  webdslorg = import ../webdslapps/webdslorg {
    inherit webdsl fetchsvn;
  };
}
