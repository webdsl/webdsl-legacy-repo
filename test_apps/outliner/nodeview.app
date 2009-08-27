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
  collapsePanel(item.caption, false) {
    for(child : TreeItem in item.children) {
      detailView(child)
    }  
    itemCreator(item)
  }
}

define viewText(item: TextNode) {
  output(item.contents)
}

define viewImage(item: ImageNode) {
  output(item.image)
}

define treeviewHeader(item: HeaderNode) {
  output(item.caption)
}

define treeviewText(item: TextNode) {
  output(item.contents)
}

define treeviewImage(item: ImageNode) {
  output(item.image)
}

define itemCreator(parent: HeaderNode) {
  collapsePanel("Add Item",true) {
    form {
      var caption: String := "<new header>";
      input(caption)
      action("Add", newheaderac(parent,caption))

      action newheaderac(parent: HeaderNode, caption: String) {
        var h : HeaderNode := HeaderNode {
          caption := caption,
          depth := parent.depth + 1,
          parent := parent
        };
        h.save();
//        parent.children.add(h);
        replace(detailView, detailView(parent)); //closest detailView
      }  		
    }
  }
}