module dnd

section templates

define no-span dndOnce() {
  <script>
    dojo.require("dojo.dnd.Source")
    dojo.require("dojo.parser")
  </script>
}


/* 
  A drag and drop region. Acts as drop target, and any dndItem inside it can be dragged.   
  Parameters:
    id: the unique identifier of the object which owns this drop region
    withHandles: if true, a handle needs to be specified for each item
      (see http://www.sitepen.com/blog/2008/10/24/inside-dojo-dnd-drag-handles/)
  
  Attributes:
    style
  
  Events:
    ondrop(item, target, index). Note that target equals id. 
   
  Elements:
    might be instances of dndItem
*/
define no-span dndSource(id: String, withHandles : Bool) {
  <script> 
  dojo.subscribe("/dnd/drop", function(source,nodes,copy,destination) {
      if (destination != null && nodes != null && destination.node.id == "~id") {
        var dest = destination.node.id; 
        if (nodes.length == 1) { 
          var item = nodes[0].id; 
          if (item != destination.node.id) {
            window.setTimeout(function() {
              var k = 0;
              for (k=0,e=nodes[0]; e = e.previousSibling; ++k);
              console.log("moved: " + item + " to " + dest + " index: "+k);
              ~event(ondrop,[item := "item", target := "dest", index := "\"\"+ k"])	
            },200); //timeout to be able to update index first
            return true;
          } 
        } 
      } 
      return false; 
    });
  </script>

  <div dojoType="dojo.dnd.Source" jsId=id id=id class="source" style=attribute("style","") withHandles= withHandles> 
    elements()
  </div>
}

/* needs to be used inside dndItem when dndSource's withHanldes is true 
  Attributes:
    style
    
  Elements

*/
define no-span dndHandle() {
  <span class="dojoDndHandle" style=attribute("style","")>
    elements()
  </span>
}

define no-span dndItem(id: String) {
  <div class="dojoDndItem" jsId=id id=id style=attribute("style","")>
    elements()
  </div>
}

define no-span dndTarget(id: String) {
  <div dojoType="dojo.dnd.Target" jsId=id id=id class="target" style=attribute("style","")>
    elements()
  </div>
}