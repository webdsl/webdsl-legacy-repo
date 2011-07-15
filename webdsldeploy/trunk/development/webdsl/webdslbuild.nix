{stdenv, webdsl, apacheAnt}:
{name, src, db ? "mysql", dbserver, dbname, dbuser, dbpassword, dbmode ? "update", rootapp ? false}:

stdenv.mkDerivation {
  inherit name src;

  buildInputs = [ webdsl apacheAnt ];
  buildPhase =
  ''
    ulimit -s unlimited
    ( echo "appname=${name}"
      echo "db=${db}"
      echo "dbserver=${dbserver}"
      echo "dbname=${dbname}"
      echo "dbuser=${dbuser}"
      echo "dbpassword=${dbpassword}"
      echo "dbmode=${dbmode}"
      echo "indexdir=/var/tomcat/temp/indexes/${name}"
      echo "rootapp=${if rootapp then "true" else "false"}"
    ) > application.ini
    webdsl war
  '';
  installPhase = ''
    mkdir -p $out/webapps
    cp .servletapp/*.war $out/webapps
  '';
}
