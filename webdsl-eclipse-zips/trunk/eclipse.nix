{ updatesites
, installIUs 
, basename 
, nixpkgs ? /etc/nixos/nixpkgs
, extraBuildInputs ? []
}:
rec {
  pkgs = import nixpkgs { };

  eclipseWin = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/indigo/SR2/eclipse-SDK-3.7.2-win32.zip ;
      sha256 = "e6938e37414c2c67067cbabb44df5d994270b1999b404635ca4c1bed30ab6906";
    };
    
  eclipseWin64 = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/indigo/SR2/eclipse-SDK-3.7.2-win32-x86_64.zip ;
      sha256 = "2e7779426a3d8eef0eba9e156ed20b3491eb6973c4c91edcf632ceef414a2849";
    };
    
  eclipseLinux = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/indigo/SR2/eclipse-SDK-3.7.2-linux-gtk.tar.gz ;
      sha256 = "f2cce7db448fa1209452605a653d82b7db17a844a86ed3bdb07e265a483c56c7";
    };

  eclipseLinux64 = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/indigo/SR2/eclipse-SDK-3.7.2-linux-gtk-x86_64.tar.gz ;
      sha256 = "e57122c79617de3469236c49fdb5b7384b2f2f64674918169ae142aeceb6c459";
    };

  eclipseMac = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/indigo/SR2/eclipse-SDK-3.7.2-macosx-cocoa.tar.gz ;
      sha256 = "405bd23b3ff5299abc8363cbb4dd6cadfeac5c89f2f0f9d96a4743ffc25a08e7"; 
    };

  eclipseMac64 = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/indigo/SR2/eclipse-SDK-3.7.2-macosx-cocoa-x86_64.tar.gz ;
      sha256 = "6e3faa7ce6c3ac3fe8a5035c193d6450129e0b851954b938f36a03fcf0e9d6c1";
    };

  eclipseZip = { name, eclipse, system }:
    let 
      eclipseini = (if system == "i686-darwin" || system == "x86_64-darwin" then "Eclipse.app/Contents/MacOS/" else "" ) + "eclipse.ini";
    in with pkgs.lib;
    pkgs.stdenv.mkDerivation rec {
      __noChroot = true;

      inherit name;
      buildInputs = [pkgs.jdk pkgs.zip pkgs.unzip pkgs.perl] ++ extraBuildInputs; 
   

      buildCommand = ''
        ALL_SITES="${concatStringsSep "," updatesites}"
        echo "ALL_SITES: $ALL_SITES"

        echo "Copying eclipse..."
        mkdir data 
        ${if system == "i686-cygwin" then "unzip ${eclipse}" else "tar xzf ${eclipse}"}
        cd eclipse
        mkdir -p configuration/.settings

        # remove vm args
        sed -i 's|-Xms[0-9]*m||' ${eclipseini}
        sed -i 's|-Xss[0-9]*m||' ${eclipseini}
        sed -i 's|-Xmx[0-9]*m||' ${eclipseini}
        sed -i 's|-XX:MaxPermSize=[0-9]*m||' ${eclipseini}
        sed -i '/^$/d' ${eclipseini}
        perl -pi -e "s/^\r\n//" ${eclipseini}


        # add own default vmwargs
        echo "-Xms256m" >> ${eclipseini}
        echo "-Xss8m" >> ${eclipseini}
        echo "-Xmx1024m" >> ${eclipseini}
        echo "-XX:MaxPermSize=256m" >> ${eclipseini}
        echo "-server" >> ${eclipseini}

        # Copy predefined settings (workspace location)
        cp ${./org.eclipse.ui.ide.prefs} configuration/.settings


        ${concatMapStrings (installIU: ''
            echo "Installing Editor ${installIU}..."
            ${if pkgs.stdenv.system == "x86_64-darwin" || pkgs.stdenv.system == "i686-darwin" then "/usr/bin/" else ""}java -Xmx512m -jar plugins/org.eclipse.equinox.launcher_*.jar  \
                 -application org.eclipse.equinox.p2.director \
                 -metadataRepository $ALL_SITES \
                 -artifactRepository $ALL_SITES \
                 -installIU ${installIU} \
                 -data ../data \
                 -consolelog
          '') installIUs
        }

        cd ..
        zip -r ${name} eclipse
        cp ${name} $out
    '';
  } ;

  zipWin     = eclipseZip { name = "eclipsewin.zip";     eclipse = eclipseWin;     system = "i686-cygwin";   } ;
  zipWin64   = eclipseZip { name = "eclipsewin64.zip";   eclipse = eclipseWin64;   system = "i686-cygwin";   } ;
  zipLinux   = eclipseZip { name = "eclipselinux.zip";   eclipse = eclipseLinux;   system = "i686-linux";    } ;
  zipLinux64 = eclipseZip { name = "eclipselinux64.zip"; eclipse = eclipseLinux64; system = "x86_64-linux";  } ;
  zipMac     = eclipseZip { name = "eclipsemac.zip";     eclipse = eclipseMac;     system = "i686-darwin";   } ;
  zipMac64   = eclipseZip { name = "eclipsemac64.zip";   eclipse = eclipseMac64;   system = "x86_64-darwin"; } ;

  zips =  
    pkgs.stdenv.mkDerivation {
      name = "${basename}-zips";
      buildCommand = ''
        mkdir -p $out
        ln -s ${ zipLinux64 } $out/eclipselinux64.zip
        ln -s ${ zipLinux } $out/eclipselinux.zip
        ln -s ${ zipMac } $out/eclipsemac.zip
        ln -s ${ zipMac64 } $out/eclipsemac64.zip
        ln -s ${ zipWin } $out/eclipsewin.zip
        ln -s ${ zipWin64 } $out/eclipsewin64.zip
        
       ensureDir $out/nix-support
       echo "file zip  $out/eclipselinux64.zip" >> $out/nix-support/hydra-build-products
       echo "file zip  $out/eclipselinux.zip" >> $out/nix-support/hydra-build-products
       echo "file zip  $out/eclipsemac.zip" >> $out/nix-support/hydra-build-products
       echo "file zip  $out/eclipsemac64.zip" >> $out/nix-support/hydra-build-products
       echo "file zip  $out/eclipsewin.zip" >> $out/nix-support/hydra-build-products
       echo "file zip  $out/eclipsewin64.zip" >> $out/nix-support/hydra-build-products
       '';
    };
    
}

