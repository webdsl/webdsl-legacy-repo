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
      
      #{ device = "none"; fsType = "tmpfs"; mountPoint = "/tmp"; options = "size=16G"; neededForBoot = true; }
    ];

  swapDevices = [
    { label = "swap"; }
  ];

  nixpkgs.system = "x86_64-linux";
  
  nix.maxJobs = 48;

  environment.etc = [ { mode = "0644"; source = ./my.cnf; target = "my.cnf"; } ];
  
  environment.systemPackages = 
    [ pkgs.emacs pkgs.subversion pkgs.sysstat pkgs.hdparm pkgs.sdparm # pkgs.lsiutil 
      pkgs.htop pkgs.sqlite pkgs.iotop pkgs.lm_sensors pkgs.gitFull pkgs.hwloc
      pkgs.lsof pkgs.numactl pkgs.gcc pkgs.mc
    ];

  services.openssh.enable = true;
  
  deployment.targetHost = "shelley";
}
