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
  };
  
}
