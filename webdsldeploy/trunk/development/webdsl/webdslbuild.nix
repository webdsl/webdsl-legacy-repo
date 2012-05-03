{stdenv, webdsl, apacheAnt}:
{ name, src, dbserver, dbname, dbuser, dbpassword, dbmode ? "update", rootapp ? false, replication ? false
, preBuild ? "", postInstall ? "", preConfigure ? ""
}:

let
  war = stdenv.mkDerivation {
    inherit name src;

    buildInputs = [ webdsl apacheAnt ];
    
    inherit preBuild postInstall preConfigure;
    
    buildPhase =
    ''
      ulimit -s unlimited
      ( echo "appname=${name}"
        echo "db=jndi"
        echo "dbmode=${dbmode}"
        echo "dbjndipath=java:/comp/env/jdbc/${dbname}"
        echo "indexdir=/var/tomcat/temp/indexes/${name}"
        echo "rootapp=${if rootapp then "true" else "false"}"
      ) > application.ini
      webdsl war
    '';
    installPhase = ''
      mkdir -p $out/webapps
      cp .servletapp/*.war $out/webapps
    '';
  };
in
stdenv.mkDerivation {
  inherit name;
  buildCommand = ''
    mkdir -p $out/conf/Catalina
    cat > $out/conf/Catalina/${if rootapp then "ROOT" else name}.xml <<EOF
    <Context>
      <Resource name="jdbc/${dbname}" auth="Container" type="javax.sql.DataSource"
                driverClassName="${if replication then "com.mysql.jdbc.ReplicationDriver" else "com.mysql.jdbc.Driver"}"
                maxActivate="100" maxIdle="30" maxWait="10000"
                username="${dbuser}" password="${dbpassword}"
                url="jdbc:mysql://${dbserver}:3306/${dbname}?useServerPrepStmts=false&amp;characterEncoding=UTF-8&amp;useUnicode=true&amp;autoReconnect=true${if replication then "&amp;roundRobinLoadBalance=true" else ""}" />
    </Context>
    EOF
    mkdir -p $out/webapps
    ln -s ${war}/webapps/*.war $out/webapps
  '';
}
