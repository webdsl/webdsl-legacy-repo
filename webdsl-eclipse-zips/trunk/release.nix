let
  pkgs = import /etc/nixos/nixpkgs {};

  eclipseWin = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/helios/SR1/eclipse-SDK-3.6.1-win32.zip ;
      sha256 = ""; 
    };

  eclipseLinux = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/helios/SR1/eclipse-SDK-3.6.1-linux-gtk.tar.gz ;
      sha256 = ""; 
    };

  eclipseLinux64 = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/helios/SR1/eclipse-SDK-3.6.1-linux-gtk-x86_64.tar.gz ;
      sha256 = ""; 
    };

  eclipseMac = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/helios/SR1/eclipse-SDK-3.6.1-macosx-cocoa.tar.gz ;
      sha256 = ""; 
    };

  eclipseMac64 = pkgs.fetchurl {
      url = http://download.springsource.com/release/ECLIPSE/helios/SR1/eclipse-SDK-3.6.1-macosx-cocoa-x86_64.tar.gz ;
      sha256 = ""; 
    };

  eclipseZip = { name, eclipse, system }:
    let pkgs = import /etc/nixos/nixpkgs { inherit system; };
        eclipseini = (if pkgs.stdenv.isDarwin then "Eclipse.app/Contents/MacOS/" else "" ) + "eclipse.ini";
    in
    pkgs.stdenv.mkDerivation rec {

      inherit name;
      buildInputs = [pkgs.jdk pkgs.zip pkgs.unzip pkgs.perl]; 

      buildCommand = ''
        WEBDSL_SITE=http://www.webdsl.org/update
        SPOOFAX_SITE=http://www.lclnet.nl/update
        WTP_SITE=http://download.eclipse.org/webtools/updates

        # emf is needed by org.eclipse.jst.ws.axis.creation.ui, org.eclipse.jst.ws.consumption
        EMF_SITE=http://download.eclipse.org/modeling/emf/updates/releases/

        ALL_SITES="$WEBDSL_SITE,$SPOOFAX_SITE,$EMF_SITE,$WTP_SITE"

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

        echo "Installing Editor..."

        java -jar plugins/org.eclipse.equinox.launcher_*.jar  \
             -application org.eclipse.equinox.p2.director \
             -metadataRepository $ALL_SITES \
             -artifactRepository $ALL_SITES \
             -installIU webdsl.editor.feature.feature.group \
             -data ../data \
             -consolelog

        cd ..
        zip -r ${name} eclipse
        cp ${name} $out
    '';
  } ;
in 
  pkgs.stdenv.mkDerivation {
    name = "webdsl-zips";
    buildCommand = ''
      mkdir -p $out
      ln -s ${ eclipseZip { name = "eclipsewin.zip";     eclipse = eclipseWin;     system = "i686-cygwin";   } } $out/eclipsewin.zip
      ln -s ${ eclipseZip { name = "eclipselinux64.zip"; eclipse = eclipseLinux64; system = "x86_64-linux";  } } $out/eclipselinux64.zip
      ln -s ${ eclipseZip { name = "eclipselinux.zip";   eclipse = eclipseLinux;   system = "i686-linux";    } } $out/eclipselinux.zip
      ln -s ${ eclipseZip { name = "eclipsemac64.zip";   eclipse = eclipseMac64;   system = "x86_64-darwin"; } } $out/eclipsemac64.zip
      ln -s ${ eclipseZip { name = "eclipsemac.zip";     eclipse = eclipseMac;     system = "i686-darwin";   } } $out/eclipsemac.zip
    '';


}
