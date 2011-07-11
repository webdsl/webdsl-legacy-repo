{ pkgs ? import (builtins.getEnv "NIXPKGS_ALL") {}
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
    if (target ? mysql && target.mysql) then targetName else searchMySQLServer (builtins.tail targetNames)
  ;
  
  webdslbuild = (import ./top-level/all-packages.nix { inherit pkgs; }).webdslbuild;
  
  webapps = map (applicationConfig: 
      (webdslbuild {
        inherit (applicationConfig) name src;
	rootapp = if applicationConfig ? rootapp then applicationConfig.rootapp else false;
	dbserver = searchMySQLServer (builtins.attrNames distribution);
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
    require = [
      ./modules/webdsl-mysql.nix
      ./modules/webdsl-tomcat.nix
      ./modules/webdsl-httpd.nix
    ];
    
    environment.systemPackages = [ pkgs.lynx ];
  } //
  optionalAttrs (options ? mysql && options.mysql) {
    webdslmysql.enable = true;
    webdslmysql.databaseNames = map (applicationConfig: if applicationConfig ? databaseName then applicationConfig.databaseName else applicationConfig.name) applications;
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
