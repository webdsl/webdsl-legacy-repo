{ config, pkgs, ... }:

let

  aselect = import ./aselect {
    inherit (pkgs) stdenv apacheHttpd libtool;
  };

  feedback = import ./aselect/feedback.nix {
    inherit (pkgs) stdenv;
  };

in

{
  boot.loader.grub.version = 2;
  boot.loader.grub.device = "/dev/sda";
  boot.loader.grub.copyKernels = true;
  boot.kernelPackages = pkgs.linuxPackages_3_2;
  boot.supportedFilesystems = [ "nfs" ];
  boot.initrd.kernelModules = [ "ext4" "coretemp" "megaraid_sas" "ext4" ];
  boot.kernelModules = [ "acpi-cpufreq" "kvm-amd" ];

  fileSystems =
    [ { mountPoint = "/";
        label = "nixos";
        options = "noatime";
      }
    ];

  swapDevices = [
    { label = "swap"; }
  ];
  
  networking.interfaces = [
    { ipAddress = "130.161.159.25"; name = "eth1"; subnetMask = "255.255.254.0"; }
  ];
  networking.nameservers = [ "130.161.180.1" "130.161.180.65" ];
  networking.defaultGateway = "130.161.158.1";
  networking.hostName = "dutieq";
  networking.domain = "st.ewi.tudelft.nl";

  nixpkgs.system = "x86_64-linux";
  
  nix.maxJobs = 48;

  environment.etc = [ { mode = "0644"; source = ./my.cnf; target = "my.cnf"; } ];
  
  environment.systemPackages = 
    [ pkgs.emacs pkgs.subversion pkgs.sysstat pkgs.hdparm pkgs.sdparm # pkgs.lsiutil 
      pkgs.htop pkgs.sqlite pkgs.iotop pkgs.lm_sensors pkgs.gitFull pkgs.hwloc
      pkgs.lsof pkgs.numactl pkgs.mc
    ];

  services.openssh.enable = true;
  
  services.tomcat.javaOpts = "-Xms350m -Xss8m -Xmx8G -Djava.security.egd=file:/dev/./urandom -XX:MaxPermSize=512M -XX:PermSize=512M -XX:-UseGCOverheadLimit -XX:+UseCompressedOops " 
     + "-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8999 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=localhost "
     + "-XX:+HeapDumpOnOutOfMemoryError -Dorg.apache.tomcat.util.http.ServerCookie.ALLOW_EQUALS_IN_VALUE=true";
  
  deployment.targetHost = "dutieq";
  
  jobs.aselect = {
    name = "aselect";

    startOn = "started httpd";

    preStart = ''
      if [ ! -d /var/lib/aselect ]
      then
          mkdir -p /var/lib/aselect/log/aselectagent/system
          cp -a ${aselect}/work /var/lib/aselect
          chmod -R u+rw /var/lib/aselect
          cp ${feedback}/* /var/lib/aselect/work/aselectagent
      fi
    '';

    preStop = "${pkgs.jdk}/bin/java -classpath \"${aselect}/bin/aselectagent/\" StopAgent";

    exec = "${pkgs.jdk}/bin/java -Duser.dir=\"/var/lib/aselect/work/aselectagent\" -server -jar \"${aselect}/bin/aselectagent/org.aselect.agent.jar\"";
  };

  services.httpd = rec {
    enable = true;

    logPerVirtualHost = true;
    logDir = "/data/www/logs";
    logFormat = "combined";

    extraConfig = ''
      <Location /server-status>
        SetHandler server-status
        Allow from 127.0.0.1 # If using a remote host for monitoring replace 127.0.0.1 with its IP.
        Order deny,allow
        Deny from all
      </Location>

      <IfModule mod_deflate.c>
        SetOutputFilter DEFLATE

        # Don't compress
        SetEnvIfNoCase Request_URI \.(?:gif|jpe?g|png)$ no-gzip dont-vary
        SetEnvIfNoCase Request_URI \.(?:exe|t?gz|zip|bz2|sit|rar)$ no-gzip dont-vary

        #Dealing with proxy servers
        <IfModule mod_headers.c>
            Header append Vary User-Agent
        </IfModule>

      </IfModule>

      ExtendedStatus On

      <IfModule mod_aselect_filter.c>

      # The location of the error template
      aselect_filter_set_html_error_template "${aselect}/apachefilter/conf/error_template.html"

      # A-Select Agent IP and port
      aselect_filter_set_agent_address "127.0.0.1"
      aselect_filter_set_agent_port "1495"

      # Applications to protect
      aselect_filter_add_secure_app "/evaluaties" "tudfeedb39de56" "default"
      aselect_filter_add_secure_app "/weblab/aselectsignin" "tudfeedb39de56" "default"
      # Global options
      aselect_filter_set_use_aselect_bar "0"
      aselect_filter_set_redirect_mode "full"

      # Authorization
      #aselect_filter_add_authz_rule "app1" "*" "ip=127.0.0.1"

      </IfModule>

    '';

    extraModules = [
      "deflate"
      { name = "aselect_filter"; path = "${aselect}/modules/mod_aselect_filter.so"; }
    ];
  };

}
