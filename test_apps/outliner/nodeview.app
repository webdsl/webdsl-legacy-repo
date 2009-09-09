module nodeview

section templates

//dispatch to  proper view
define detailView(item: TreeItem) {
         if (item isa HeaderNode) { viewHeader(item as HeaderNode) }
  else { if (item isa TextNode)   { viewText  (item as TextNode) }
  else { if (item isa ImageNode)  { viewImage (item as ImageNode) }
  else { "Error: unsupported node type" }  }  }
}

define viewHeader(item: HeaderNode) {
  collapsePanel(false) with {
    caption() {
      container[ondblclick:=onfocus(item), id := titleloc]{
        headerItemDefaultView(item)
      }
    }
    contents() {
      for(child : TreeItem in item.children) {
        detailView(child)
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
