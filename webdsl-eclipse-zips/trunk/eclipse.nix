{ updatesites
, installIUs 
, basename 
, nixpkgs ? /etc/nixos/nixpkgs
}:
rec {
  pkgs = import nixpkgs { };

  eclipseWin = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/helios/SR1/eclipse-SDK-3.6.1-win32.zip ;
      sha256 = "08chhq5y9z3v1ld0p2mf1xakhj4vs9s25ayvcn9h748mzhaahzda"; 
    };

  eclipseLinux = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/helios/SR1/eclipse-SDK-3.6.1-linux-gtk.tar.gz ;
      sha256 = "0s48rjaswi8m5gan1zlqvfwb4l06x5nslkq41wpkrbyj9ka8gh4x"; 
    };

  eclipseLinux64 = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/helios/SR1/eclipse-SDK-3.6.1-linux-gtk-x86_64.tar.gz ;
      sha256 = "1cg9rrb5w978sdqbzz9lnli1lds9zhb6wfsj3wp725bqf1i6v9lg"; 
    };

  eclipseMac = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/helios/SR1/eclipse-SDK-3.6.1-macosx-cocoa.tar.gz ;
      sha256 = "1jdsnipbf4db0i0g9vcpnmh4mdgg874p15677wibm4righ9cj9n3"; 
    };

  eclipseMac64 = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/helios/SR1/eclipse-SDK-3.6.1-macosx-cocoa-x86_64.tar.gz ;
      sha256 = "0b3pa4vc68dcxc9db7gj1vcikinxppxs5bi3rydsxzzsycrm0vly"; 
    };

  eclipseZip = { name, eclipse, system }:
    let 
      eclipseini = (if system == "i686-darwin" || system == "x86_64-darwin" then "Eclipse.app/Contents/MacOS/" else "" ) + "eclipse.ini";
    in with pkgs.lib;
    pkgs.stdenv.mkDerivation rec {
      __noChroot = true;

      inherit name;
      buildInputs = [pkgs.jdk pkgs.zip pkgs.unzip pkgs.perl]; 

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
      '';
    };
}

