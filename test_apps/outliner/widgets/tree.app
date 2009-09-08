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
 loadDojo()
 <script>
      dojo.require("dojo.data.ItemFileWriteStore");
      dojo.require("dojo.data.ItemFileReadStore");
      dojo.require("dijit._tree.dndSource");
      dojo.require("dijit.Tree");     
      dojo.require("dojo.parser");
      dojo.require("dojo.dnd.common");
      dojo.require("dojo.dnd.Source");
      
  </script>

  var store: String := "store" + random().toString().replace("0.","_");
 
  <div dojoType="dojo.data.ItemFileWriteStore" jsId=store url=storeURL>
      <script type="dojo/method" event="newItem" args="item">
           "alert(item); "   
      </script>
  </div>
     
  
  <div dojoType="dijit.tree.TreeStoreModel" jsId=store+"Model" store=store
    query="{id:'"+rootID+"'}" childrenAttr="[children]">
  </div>
  <span id= "test"/>
  <script>
  //on item tree , we only want to drop on containers, or the root node itself, not on items in the containers
    function itemTreeCheckItemAcceptance(node,source) {
      console.log(">>In checkitemacceptance");
      
      var item = dijit.getEnclosingWidget(node).item;
      console.log("node: "+node);
      console.log("item: "+item);
      console.log("item.id"+ item.id);
      
      if (source != null) 
        for (s in source.selection) {
          console.log("s: " + s);
          if (s != item.id) {
            dojo.attr(dojo.byId("test"), "_dropPosition", item.id); 
            return true;
          }
        }
      return false;
    }

  </script>
  <script language="javascript">
    rawoutput {
      //since we don't have a server, we're going to connect to the store and do a few things the server/store combination would normal be taking care of for us
/*      "dojo.connect(" output(store) ", \"onNew\", function(item, pInfo){ \n"
      "	var p = pInfo && pInfo.item;  \n"
      "	if (p) {	   \n"
      "		var currentTotal = myStore.getValues(p, \"numberOfItems\")[0]; \n"
      output(store)".setValue(p, \"numberOfItems\", ++currentTotal);  \n"
      "	} \n"
      " }); \n"
  */  
    
    
      " function dndDrop(source,nodes,copy,destination) { \n "
      " if (destination != null && nodes != null) { \n"
      " var sources = destination.getSelectedItems(); \n"
      " if (sources.length == 1 && sources[0].id.length == 1 && nodes.length == 1) { \n"
      " var item = sources[0].id[0]; \n"
//      " var target = dijit.getEnclosingWidget(nodes[0]).item.id[0]; \n"
      " var target = dojo.attr(dojo.byId(\"test\"), \"_dropPosition\"); \n" 
//      " var item = nodes[0].id;var target = source.getSelectedItems()[0].id[0]; \n"
      " console.log(\"moved: \"+item+\" to \"+target);"
      " if (item != target) { \n  " 
      event(ondrop,[item := "item", target := "target"])	
      "  return true; } } } return false; } "
    }
  </script>
  <div dojoType="dijit.Tree" id=store+"Tree" model=store+"Model" openOnClick="true" style="width:"+attribute("width","300px")+";"
    dndController="dijit._tree.dndSource" onDndDrop="dndDrop" checkItemAcceptance="itemTreeCheckItemAcceptance" persist="false">
      <script type="dojo/method" event="onClick" args="item">
           event(onselect,[id := "item.id"])    
      </script>
  </div>
}
