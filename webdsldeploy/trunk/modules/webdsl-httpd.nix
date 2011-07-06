{config, pkgs, ...}:

with pkgs.lib;

let
  cfg = config.webdslhttpd;
in
{
  options = {
    webdslhttpd = {
      enable = mkOption {
        default = false;
	description = "Whether to enable the WebDSL HTTPD configuration";
      };
      
      adminAddr = mkOption {
        description = "Web server administration address";
      };
    };
  };

  config = mkIf cfg.enable {
    services.httpd.enable = true;
    services.httpd.adminAddr = cfg.adminAddr;
      
    services.httpd.extraSubservices = [
      { serviceType = "tomcat-connector";
        stateDir = "/var/run/httpd";
        logDir = "/var/log/httpd";
      }
    ];
  };
  
}
