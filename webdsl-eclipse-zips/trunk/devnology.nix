(import ./eclipse.nix {
  basename = "devnology";
  updatesites = [
    "http://mobl-lang.org/update"
    "http://webdsl.org/update"
    "http://download.eclipse.org/releases/helios"
  ];
  installIUs = [
    "org.mobl_lang.feature.feature.group"
    "webdsl.editor.feature.feature.group"
  ];  
}).zips
