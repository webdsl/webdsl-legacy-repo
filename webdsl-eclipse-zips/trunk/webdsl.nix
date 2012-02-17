(import ./eclipse.nix {
  basename = "webdsl-1.2.9";
  updatesites = [
    "http://webdsl.org/update"
    "http://download.eclipse.org/releases/indigo"
  ];
  installIUs = [
    "webdsl.editor.feature.feature.group"
  ];  
}).zips
