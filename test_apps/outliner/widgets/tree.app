module tree

section data

entity TreeItem {
  parent -> TreeItem (inverse = TreeItem.children)
  children -> List<TreeItem>// (inverse = TreeItem.parent)
}

define no-span template loadDojo() {
  <script src="http://www.google.com/jsapi"></script>
  <script>google.load("dojo", "1.3.2");     
   djConfig = {
      afterOnLoad : false,
      addOnLoad: function() { 
//        alert('loaded');
      },
      parseOnLoad:true
    };
    loadCSS('http:\/\/o.aolcdn.com/dojo/1.3.2/dijit/themes/tundra/tundra.css');
    loadCSS('http:\/\/o.aolcdn.com/dojo/1.3.2/dojo/resources/dojo.css');
    loadCSS('http:\/\/o.aolcdn.com/dojo/1.3.2/dojo/resources/dnd.css');
    loadCSS('http:\/\/o.aolcdn.com/dojo/1.3.2/dojo/tests/dnd/dndDefault.css');
     
  </script>
}

define template dojoTree(storeURL: String, rootID: String) {
 var store: String := "store" + random().toString().replace("0.","_");
 
 <script>
    dojo.require("dojo.data.ItemFileWriteStore");
    dojo.require("dojo.data.ItemFileReadStore");
    dojo.require("dijit._tree.dndSource");
    dojo.require("dijit.Tree");     
    dojo.require("dojo.parser");
    dojo.require("dojo.dnd.common");
    dojo.require("dojo.dnd.Source");
      
    function itemTreeCheckItemAcceptance(node,source) {
      if (source != null && node != null) { 
        var item = dijit.getEnclosingWidget(node).item;
        for (s in source.selection) {
          if (s != item.id) {
            dojo.attr(dojo.byId("~store"+"Tree"), "_dropPosition", item.id); 
            return true;
          }
        }
      }
      return false;
    }

    //function dndDrop(source,nodes,copy,destination) {
    dojo.subscribe("/dnd/drop", function(source,nodes,copy,destination) {
      if (destination != null && nodes != null && destination.node.id == "~(store+"Tree")") { 
        var sources = destination.getSelectedItems();
        if (sources.length == 1 && sources[0].id.length == 1 && nodes.length == 1) { 
          var item = sources[0].id[0]; 
          var target = dojo.attr(dojo.byId("~store"+"Tree"), "_dropPosition");  
          if (item != target) {
            console.log("moved: " + item + " to " + target);
            ~event(ondrop,[item := "item", target := "target"])	
            return true; 
          } 
        } 
      } 
      return false; 
    }); 
  </script>

 
  <div dojoType="dojo.data.ItemFileWriteStore" jsId=store url=storeURL/>
  <div dojoType="dijit.tree.TreeStoreModel" jsId=store+"Model" store=store
    query="{id:'"+rootID+"'}" childrenAttr="[children]"/>

  <div dojoType="dijit.Tree" id=store+"Tree" model=store+"Model" openOnClick="true" style="width:"+attribute("width","300px")+";"
    dndController="dijit._tree.dndSource" checkItemAcceptance="itemTreeCheckItemAcceptance" persist="false"> // onDndDrop="dndDrop" 
      <script type="dojo/method" event="onClick" args="item">
           ~event(onselect,[id := "item.id"])    
      </script>
  </div>
}
