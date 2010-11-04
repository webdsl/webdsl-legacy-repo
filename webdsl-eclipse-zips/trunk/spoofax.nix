(import ./eclipse.nix {
  basename = "spoofax";
  updatesites = [
    "http://www.lclnet.nl/update"
    "http://download.eclipse.org/releases/helios"
  ];
  installIUs = [
    "org.strategoxt.imp.feature.group"
  ];  
}).zips
