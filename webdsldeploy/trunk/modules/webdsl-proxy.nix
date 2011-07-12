{pkgs, config, nodes, ...}:

with pkgs.lib;

let
  cfg = config.webdslproxy;
in
{
  options = {
    webdslproxy = {
      enable = mkOption {
        default = false;
	description = "Whether to enable the WebDSL proxy configuration";
      };
      
      adminAddr = mkOption {
        description = "Web server administration address";
      };
      
      balancerMembers = mkOption {
        description = "Identifiers of machines which are members of the loadbalancer";
      };
    };
  };

  config = mkIf cfg.enable {
    services.httpd.enable = true;
    services.httpd.adminAddr = cfg.adminAddr;
    services.httpd.extraModules = ["proxy_balancer"];

    services.httpd.extraConfig =
      ''
        ExtendedStatus on

        <Location /server-status>
          Order deny,allow
          Allow from all
          SetHandler server-status
        </Location>

        <Proxy balancer://cluster>
          Allow from all
	  ${concatMapStrings (member: "BalancerMember http://${(builtins.getAttr member nodes).config.networking.hostName} retry=0\n") (cfg.balancerMembers)}
        </Proxy>

        ProxyStatus       full
        ProxyPass         /server-status !
        ProxyPass         /       balancer://cluster/
        ProxyPassReverse  /       balancer://cluster/
    '';
  };
}
