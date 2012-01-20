{ pkgs ? import <nixpkgs> {}
, applications
, databasePassword
, distribution ? { test = { tomcat = true; httpd = true; mysql = true; }; }
, adminAddr
}:

with pkgs.lib;

let 
  searchMySQLServer = targetNames: 
    let
      targetName = builtins.head targetNames;
      target = getAttr targetName distribution;
    in
    if targetNames == [] then null else
    if ((target ? mysql && target.mysql) || (target ? mysqlMaster && target.mysqlMaster)) then targetName else searchMySQLServer (builtins.tail targetNames)
  ;
  
  mySQLSlaves = targetNames:
    let
      targetName = builtins.head targetNames;
      target = getAttr targetName distribution;
    in
    if targetNames == [] then "" else
    if (target ? mysqlSlave) then ",${targetName}" else mySQLSlaves (builtins.tail targetNames)
  ;
  
  webdslBuild = (import ./top-level/all-packages.nix { inherit pkgs; }).webdslBuild;
  webdslBuildJava = (import ./top-level/all-packages.nix { inherit pkgs; }).webdslBuildJava;
  
  webapps = map (applicationConfig: 
      (webdslBuildJava {
        inherit (applicationConfig) name src;
        rootapp = if applicationConfig ? rootapp then applicationConfig.rootapp else false;
        replication = mySQLSlaves (builtins.attrNames distribution) != "";
        dbserver = if (applicationConfig ? useLocalhostDB && applicationConfig.useLocalhostDB) then "localhost"
          else searchMySQLServer (builtins.attrNames distribution) + mySQLSlaves (builtins.attrNames distribution);
        dbname = if applicationConfig ? databaseName then databaseName else applicationConfig.name;
        dbuser = "root"; # !!! Ugly
        dbpassword = databasePassword;
        dbmode = if applicationConfig ? databaseMode then databaseMode else "update";
      })
    ) applications
  ;  
in
mapAttrs (targetName: options:
  { pkgs, ... }:
  
  {
    require = optional ((options ? mysql && options.mysql) || (options ? mysqlMaster && options.mysqlMaster) || (options ? mysqlSlave)) ./modules/webdsl-mysql.nix
            ++ optional (options ? tomcat && options.tomcat) ./modules/webdsl-tomcat.nix
            ++ optional (options ? httpd && options.httpd) ./modules/webdsl-httpd.nix
            ++ optional (options ? proxy && options.proxy) ./modules/webdsl-proxy.nix;    
  } //
  optionalAttrs ((options ? mysql && options.mysql) || (options ? mysqlMaster && options.mysqlMaster) || (options ? mysqlSlave)) {
    webdslmysql.enable = true;
    webdslmysql.databaseNames =
      if options ? mysqlSlave then [] else 
      map (applicationConfig: if applicationConfig ? databaseName then applicationConfig.databaseName else applicationConfig.name) applications;
    webdslmysql.databasePassword = databasePassword;
    
    webdslmysql.replication.role = if (options ? mysqlMaster && options.mysqlMaster) then "master" else if (options ? mysqlSlave) then "slave" else "none";
    webdslmysql.replication.serverId = if (options ? mysqlMaster && options.mysqlMaster) then 1 else if (options ? mysqlSlave) then options.mysqlSlave else 0;
    webdslmysql.replication.masterHost = searchMySQLServer (builtins.attrNames distribution);
    webdslmysql.replication.masterUser = "root"; # !!! Ugly
    webdslmysql.replication.masterPassword = databasePassword;
  } //
  optionalAttrs (options ? tomcat && options.tomcat) {
    webdsltomcat.enable = true;
    webdsltomcat.webapps = webapps;
  } //
  optionalAttrs (options ? httpd && options.httpd) {
    webdslhttpd.enable = true;
    webdslhttpd.adminAddr = adminAddr;
  } //
  optionalAttrs (options ? proxy && options.proxy) {
    webdslproxy.enable = true;
    webdslproxy.adminAddr = adminAddr;
    webdslproxy.balancerMembers = pkgs.lib.filter (targetName:
      let options = builtins.getAttr targetName distribution;
      in  
      options ? httpd && options.httpd
      ) (builtins.attrNames distribution);
  }
  
) distribution
