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
      
      databaseNames = mkOption {
        description = "Names of the WebDSL MySQL databases";
      };
      
      databasePassword = mkOption {
        description = "Password of the WebDSL MySQL database";
      };
    };
  };

  config = mkIf cfg.enable {
    services.mysql.enable = true;
    services.mysql.rootPassword = pkgs.writeTextFile { name = "mysqlpw"; text = cfg.databasePassword; };
    services.mysql.initialDatabases = map (databaseName: { name = databaseName; schema = ./schema.sql; } ) (cfg.databaseNames);
    services.mysql.initialScript = ./mysqlscript;
  };
  
}
