module dnd

section templates

define no-span dndOnce() {
  <script>
    dojo.require("dojo.dnd.Source")
    dojo.require("dojo.parser")
  </script>
}


define no-span dndSource(id: String) {
  
  <script> 
  dojo.subscribe("/dnd/drop", function(source,nodes,copy,destination) {
      if (destination != null && nodes != null && destination.node.id == "~id") {
        var dest = destination.node.id; 
        if (nodes.length == 1) { 
          var item = nodes[0].id; 
          if (item != destination.node.id) {
            window.setTimeout(function() {
              var k;
              for (k=0,e=nodes[0]; e = e.previousSibling; ++k);
              console.log("index: "+k);
              console.log("moved: " + item + " to " + dest + " index: "+k);
              ~event(ondrop,[item := "item", target := "dest", index := "k"])	
            },200); //timeout to be able to update index first
            return true;
          } 
        } 
      } 
      return false; 
    });
  </script>

  <div dojoType="dojo.dnd.Source" jsId=id id=id class="source"> 
    elements()
  </div>
}

define no-span dndItem(id: String){ //, dndType: String) {
  <div class="dojoDndItem" jsId=id id=id> //dndType=dndType>
    elements()
  </div>
}

define no-span dndTarget(id: String) {
  <div dojoType="dojo.dnd.Target" jsId=id id=id class="target">// accept="blue,darkred">
    elements()
  </div>
}