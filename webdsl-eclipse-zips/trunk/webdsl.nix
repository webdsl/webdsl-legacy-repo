(import ./eclipse.nix {
  basename = "webdsl-1.2.7.2";
  updatesites = [
    "http://webdsl.org/update"
    "http://download.eclipse.org/releases/helios"
  ];
  installIUs = [
    "webdsl.editor.feature.feature.group"
  ];  
}).zips