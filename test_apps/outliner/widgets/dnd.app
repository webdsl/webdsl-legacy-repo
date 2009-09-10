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
  dojo.subscribe("/dnd/drop/before", function(source,nodes,copy,destination) {
      if (destination != null && nodes != null && destination.node.id == "~id") { 
        if (nodes.length == 1) { 
          var item = nodes[0].id; 
          if (item != destination.node.id) {
            console.log("moved: " + item + " to " + destination.node.id + (source.before?" before ":" after "+ destination.targetAnchor.id));
            ~event(ondrop,[item := "item", target := "destination.node.id", before := "source.before", anchor:= "destination.targetAnchor.id"])	
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