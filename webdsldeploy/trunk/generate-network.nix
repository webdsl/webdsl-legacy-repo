{deploySpec, pkgs}:

with pkgs.lib;

let 
  searchMySQLServer = targetNames: 
    let
      targetName = builtins.head targetNames;
      target = getAttr targetName (deploySpec.distribution);
    in
    if targetNames == [] then null else
    if (target ? mysql && target.mysql) then targetName else searchMySQLServer (builtins.tail targetNames)
  ;
  
  webapps = [ ((import ./top-level/all-packages.nix {}).webdslbuild {
      name = deploySpec.name;
      src = deploySpec.src;
      dbserver = searchMySQLServer (builtins.attrNames (deploySpec.distribution));
      dbname = deploySpec.databaseName; # !!! Ugly
      dbuser = "root"; # !!! Ugly
      dbpassword = deploySpec.databasePassword;
      dbmode = deploySpec.databaseMode;
      rootapp = deploySpec.rootapp;
    })
  ];
in
mapAttrs (targetName: options:
  { pkgs, ... }:
  
  {
    require = [
      ./modules/webdsl-mysql.nix
      ./modules/webdsl-tomcat.nix
      ./modules/webdsl-httpd.nix
    ];
    
    environment.systemPackages = [ pkgs.lynx ];
    
  } //
  optionalAttrs (options ? mysql && options.mysql) {
    webdslmysql.enable = true;
    webdslmysql.databaseName = deploySpec.databaseName;
    webdslmysql.databasePassword = deploySpec.databasePassword;
  } //
  optionalAttrs (options ? tomcat && options.tomcat) {
    webdsltomcat.enable = true;
    webdsltomcat.webapps = webapps;
  } //
  optionalAttrs (options ? httpd && options.httpd) {
    webdslhttpd.enable = true;
    webdslhttpd.adminAddr = deploySpec.adminAddr;
  }
) (deploySpec.distribution)
