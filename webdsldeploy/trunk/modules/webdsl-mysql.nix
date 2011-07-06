{config, pkgs, ...}:

with pkgs.lib;

let
  cfg = config.webdslmysql;
in
{
  options = {
    webdslmysql = {
      enable = mkOption {
        default = false;
	description = "Whether to enable the WebDSL MySQL configuration";
      };
      
      databaseName = mkOption {
        description = "Name of the WebDSL MySQL database";
      };
      
      databasePassword = mkOption {
        description = "Passwork of the WebDSL MySQL database";
      };
    };
  };

  config = mkIf cfg.enable {
    services.mysql.enable = true;
    services.mysql.rootPassword = pkgs.writeTextFile { name = "mysqlpw"; text = cfg.databasePassword; };
    services.mysql.initialDatabases = [ { name = cfg.databaseName; schema = ./schema.sql; } ];
    services.mysql.initialScript = ./mysqlscript;
  };
  
}
