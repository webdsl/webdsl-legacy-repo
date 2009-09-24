module loaddojo

section templates

//if remote is false, the dojo sources are expected in <project-dir>/javascript>/dojo-release-<version>
//if true, they are loaded from the google and aol CDN's
define no-span template loadDojo(remote: Bool, version: String) {
  <script>
    function loadDojoCSS(prefix) {
      loadCSS(prefix+"/dijit/themes/tundra/tundra.css");
      loadCSS(prefix+"/dojo/resources/dojo.css");
      loadCSS(prefix+"/dojo/resources/dnd.css");
    }
  </script>
  if (remote) {
    <script type="text/javascript" src="http://www.google.com/jsapi"> </script>
    <script>
      loadDojoCSS("http:\/\/o.aolcdn.com/dojo/"+"~version");
      google.load("dojo", "~version");     
      djConfig = {
        afterOnLoad : false,
        addOnLoad: function() {
          console.log("Dojo finished loading: "+"~version"+" (remote: "+"~remote"+")"); 
        },
        parseOnLoad:true
      };
    </script>
  }
  if (!remote) {
    <script 
      type="text/javascript" 
      src=baseURL()+"/javascript/dojo-release-"+version+"/dojo/dojo.js"
      djConfig="parseOnLoad:true, isDebug:true" //, addOnLoad: function() {console.log(\"Dojo finished loading: "+version+" (remote: "+remote+"\")\"); } "
    ></script>
    <script>
      //override the post processor
      __ajax_postprocessor = function(node) { 
        if (typeof(dojo) != 'undefined')
          dojo.parser.parse(node);	
      };
      loadDojoCSS("~(baseURL())"+"/javascript/dojo-release-"+"~version");
    </script>
  }
}