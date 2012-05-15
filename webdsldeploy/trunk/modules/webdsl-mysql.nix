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
      
      replication = {
        role = mkOption {
          description = "Replication role of the MySQL node";
          default = "none";
        };
        
        serverId = mkOption {
          description = "Id of the MySQL. Must be unique and higher than 1 for read slaves";
          default = 1;
        };
        
        masterHost = mkOption {
          description = "Hostname of the MySQL master";
        };
        
        masterUser = mkOption {
          description = "Username of the MySQL master";
        };
        
        masterPassword = mkOption {
          description = "Password of the MySQL master";
        };
      };
    };
  };

  config = mkIf cfg.enable {
    services.mysql.enable = true;
    services.mysql.package = pkgs.mysql55;
    services.mysql.rootPassword = pkgs.writeTextFile { name = "mysqlpw"; text = cfg.databasePassword; };
    services.mysql.initialDatabases = map (databaseName: { name = databaseName; schema = ./schema.sql; } ) (cfg.databaseNames);
    services.mysql.initialScript = pkgs.writeText "mysqlscript" ''
      grant all on *.* to 'root'@'%' identified by "";
      ${optionalString (cfg.replication.role == "master") ''
          grant replication slave on *.* to '${cfg.replication.masterUser}'@'%';
      ''}
    '';
    
    services.mysql55.replication.role = cfg.replication.role;
    services.mysql55.replication.serverId = cfg.replication.serverId;
    services.mysql55.replication.masterHost = cfg.replication.masterHost;
    services.mysql55.replication.masterUser = cfg.replication.masterUser;
    services.mysql55.replication.masterPassword = cfg.replication.masterPassword;
  };
}
