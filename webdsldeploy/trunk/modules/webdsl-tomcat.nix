{config, pkgs, ...}:

with pkgs.lib;

let
  cfg = config.webdsltomcat;
in

{
  options = {
    webdsltomcat = {
      enable = mkOption {
        description = "Whether to enable the WebDSL tomcat configuration";
	default = false;
      };
      
      webapps = mkOption {
        description = "WebDSL WAR files to host";
	default = [];
      };
    };
  };

  config = mkIf cfg.enable {
    services.tomcat.enable = true;
    services.tomcat.webapps = cfg.webapps;
    services.tomcat.commonLibs = [ "${pkgs.mysql_jdbc}/share/java/mysql-connector-java.jar" ];
  };
  
}
