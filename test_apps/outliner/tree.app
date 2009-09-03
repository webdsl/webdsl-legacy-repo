module tree

section data

entity TreeItem {
  parent -> TreeItem (inverse = TreeItem.children)
  children -> List<TreeItem>// (inverse = TreeItem.parent)
}

define no-span template loadDojo() {
  <script src="http://www.google.com/jsapi"></script>
  <script>google.load("dojo", "1.3.0");     
   djConfig = {
      afterOnLoad : false,
      addOnLoad: function() { 
//        alert('loaded');
      },
      parseOnLoad:true
    };
//    loadCSS('http:\/\/o.aolcdn.com/dojo/1.3.0/dijit/themes/tundra/tundra.css');
    loadCSS('http:\/\/o.aolcdn.com/dojo/1.3.0/dojo/resources/dojo.css'); 
  </script>
}

define template dojoTree(storeURL: String, rootID: String) {
 loadDojo()
 <script>
      dojo.require("dojo.data.ItemFileReadStore");
      dojo.require("dijit.Tree");     
      dojo.require("dojo.parser");
  </script>

  var store: String := "store" + random().toString().replace("0.","_");
 
  <div dojoType="dojo.data.ItemFileReadStore" jsId=store url=storeURL></div>
  
  <div dojoType="dijit.tree.TreeStoreModel" jsId=store+"Model" store=store
    query="{id:'"+rootID+"'}" childrenAttr="[children]">
  </div>
  
  <div dojoType="dijit.Tree" id=store+"Tree" model=store+"Model" openOnClick="true" style="width:600px;height:400px">
      <script type="dojo/method" event="onClick" args="item">
           rawoutput { output(event("onselect","&id=\"+item.id+\"")) }    
      </script>
  </div>
}
