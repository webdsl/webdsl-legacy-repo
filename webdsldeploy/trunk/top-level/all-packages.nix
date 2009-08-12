let pkgs = import /etc/nixos/nixpkgs/pkgs/top-level/all-packages.nix { }; in
with pkgs;

rec {    
  webdsl = import ../development/webdsl {
    inherit stdenv fetchurl pkgconfig getopt apacheAnt;
    inherit (strategoPackages017) aterm sdf strategoxt javafront;
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
