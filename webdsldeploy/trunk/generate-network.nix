{ pkgs ? import (builtins.getEnv "NIXPKGS_ALL") {}
, name, src
, databaseName ? name
, databasePassword, databaseMode ? "update"
, rootapp ? false
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
    if (target ? mysql && target.mysql) then targetName else searchMySQLServer (builtins.tail targetNames)
  ;
  
  webapps = [ ((import ./top-level/all-packages.nix {}).webdslbuild {
      inherit name src rootapp;
      dbserver = searchMySQLServer (builtins.attrNames distribution);
      dbname = databaseName;
      dbuser = "root"; # !!! Ugly
      dbpassword = databasePassword;
      dbmode = databaseMode;
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
    webdslmysql.databaseName = databaseName;
    webdslmysql.databasePassword = databasePassword;
  } //
  optionalAttrs (options ? tomcat && options.tomcat) {
    webdsltomcat.enable = true;
    webdsltomcat.webapps = webapps;
  } //
  optionalAttrs (options ? httpd && options.httpd) {
    webdslhttpd.enable = true;
    webdslhttpd.adminAddr = adminAddr;
  }
) distribution
