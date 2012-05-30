{ config, pkgs, ... }:

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
}
