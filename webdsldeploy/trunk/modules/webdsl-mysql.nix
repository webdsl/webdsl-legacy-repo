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
	  default = "none";
	};
	
	serverId = mkOption {
	  default = 1;
	};
	
	masterHost = mkOption {
	};
	
	masterUser = mkOption {
	};
	
	masterPassword = mkOption {
	};
      };
    };
  };

  config = mkIf cfg.enable {
    services.mysql.enable = true;
    services.mysql.rootPassword = pkgs.writeTextFile { name = "mysqlpw"; text = cfg.databasePassword; };
    services.mysql.initialDatabases = map (databaseName: { name = databaseName; schema = ./schema.sql; } ) (cfg.databaseNames);
    services.mysql.initialScript = pkgs.writeText "mysqlscript" ''
      grant all on *.* to 'root'@'%' identified by "";
      ${optionalString (cfg.replication.role == "master") ''
          grant replication slave on *.* to '${cfg.replication.masterUser}'@'%';
      ''}
    '';
    
    services.mysql.replication.role = cfg.replication.role;
    services.mysql.replication.serverId = cfg.replication.serverId;
    services.mysql.replication.masterHost = cfg.replication.masterHost;
    services.mysql.replication.masterUser = cfg.replication.masterUser;
    services.mysql.replication.masterPassword = cfg.replication.masterPassword;
  };
}
