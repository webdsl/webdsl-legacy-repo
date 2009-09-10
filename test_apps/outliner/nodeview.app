module nodeview

section templates

//dispatch to  proper view
define detailView(item: TreeItem) {
  dndOnce()
  //dndSource("detailViewTree") {
    nodeView(item)
  //}
}

define nodeView(item: TreeItem) {
         if (item isa HeaderNode) { viewHeader(item as HeaderNode) }
  else { if (item isa TextNode)   { viewText  (item as TextNode) }
  else { if (item isa ImageNode)  { viewImage (item as ImageNode) }
  else { "Error: unsupported node type" }  }  }
}

define viewHeader(item: HeaderNode) {
  action dropelement(item: String, target: String, before: String, anchor: String) {
    var targetNode: HeaderNode := loadHeaderNode(UUIDFromString(target));
    var itemNode: TreeItem := loadTreeItem(UUIDFromString(item));
    
    //update depth
    if (itemNode isa HeaderNode) {
      var h: HeaderNode := itemNode as HeaderNode;
      h.depth := targetNode.depth + 1;
    }
    
    if (anchor == "atend") {
    //  targetNode.children.add(item);
    }
    if (anchor == "" && before=="true") {
//      targetNode.children.insert(0, item);
    }
    
    itemNode.parent := targetNode;
  //  relocate(outliner(doc));
  }
  collapsePanel(false) with {
    caption() {
  /*    dndSource(item.id.toString()) {
      dndItem(item.id.toString()) {
       "[0000]"
      }
      }
   */   container[ondblclick:=onfocus(item), id := titleloc]{
          headerItemDefaultView(item)
      }
    }
    contents() {
      dndSource(item.id.toString())
      //	[ondrop:= dropelement(null,null,null,null)] 
      {
        for(child : TreeItem in item.children) {
          dndItem(child.id.toString()) {
            nodeView(child)
          }
        }
        dndItem("atend") {
          "[ here ]"
        }
      }
      placeholder itemadder {}
    }
  }
  
  action onfocus(item: HeaderNode) {
    replace(itemadder, itemCreator(item));
    replace(titleloc,  headerItemEditor(item));
  }
}

define no-span viewText(item: TextNode) {
  block[id := viewText, class :="scopediv viewText", style := "background-color : #CCCCCC; "] {
    form {
      inplaceTextArea(item.contents)
      action("Save", saveac())[id := submit]
    }
  }
  action saveac() {
    replace(this, viewText(item));
  }
}

define viewImage(item: ImageNode) {
  block[id := viewImage, class :="scopediv viewText", style := "background-color : #CCCCCC; ", onclick := editac(item)] {
    output(item.image)
  }
  action editac(item: ImageNode) {
    replace (this, editImage(item));
  }
}


define headerItemDefaultView(item: HeaderNode) {
  output(item.caption)
}
