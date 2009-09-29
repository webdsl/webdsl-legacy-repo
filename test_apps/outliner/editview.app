module editview

section templates

define template documentTree(doc: Document) {
  dojoTree(navigate(documentoutline(doc)), doc.root.id.toString())
    [onselect:=selectHeader(null), width:= "180px"]
  
  action selectHeader(id: String) {
    var n: HeaderNode := loadHeaderNode(UUIDFromString(id));
    replace(detailView, detailView(n));
  }
}

//dispatch to  proper view
define detailView(item: HeaderNode) {
  dndOnce()
  showPath(item)
  
  spacer
  nodeView(item)
}

define no-span nodeView(item: TreeItem) {
  block[class:=[scopediv, nodeView]] {
         if (item isa HeaderNode) { viewHeader(item as HeaderNode) }
  else { if (item isa TextNode)   { viewText  (item as TextNode) }
  else { if (item isa ImageNode)  { viewImage (item as ImageNode) }
  else { "Error: unsupported node type" }  }  }
  }
}

define showPath(item: HeaderNode) {
  var path: List<HeaderNode> := List<HeaderNode>();
  init {
    var cur: HeaderNode := item;
    while(cur != null) {
      path.add(cur);
      cur := cur.parent as HeaderNode;
    }
  }
  
  for(i: Int from path.length -1 to 0) {
    image("/images/right.png")
    navigate[
      onclick:= loadView(path.get(i))
    ]{ output(path.get(i).caption) }
    
  } 
  image("/images/right.png")
  output(item.caption)
  
  action loadView(item: HeaderNode) {
    replace (detailView, detailView(item));
  }
}

section newitem

define no-span itemadderhidden(parent: HeaderNode) {
  container[id:= itemadder] {
    image("/images/right.png") [
      onclick:= action { replace(itemadder, itemaddervisible(parent)); }, 
      height:= "16px"
    ]
  }
}

define no-span itemaddervisible(parent: HeaderNode) {
  container[id:= itemadder] {
    rndButton("left",false)[onclick:= action { replace(itemadder, itemadderhidden(parent)); }, class:= firstbtn, style:="height:24px"]
    itemCreator(parent)
  }
}

define itemCreator(parent: HeaderNode) {
    rndButton("header",false)[onclick:= newheaderac(parent), class:=middlebtn]
    rndButton("text",false)  [onclick:= newtextac(parent),   class:=middlebtn]
    rndButton("image",false) [onclick := newimgac(parent),   class:=lastbtn]

    action newheaderac(parent: HeaderNode) {
      var h : HeaderNode := HeaderNode {
        caption := "<double click to enter a new name>",
        depth := parent.depth + 1,
        parent := parent
      };
      h.save();
      parent.children.add(h);
      replace(nodeView, nodeView(parent)); 
      replace(documentTree, documentTree(getDocument(parent)));
    }  		
    action newtextac(parent: HeaderNode) {
      var h : TextNode := TextNode {
        contents := "<double click to edit>",
        parent := parent
      };
      h.save();
      parent.children.add(h);
      replace(nodeView, nodeView(parent)); 
    }  		
    action newimgac(parent: HeaderNode) {
      var h : ImageNode := ImageNode {
        parent := parent
      };
      h.save();
      parent.children.add(h);
      replace(nodeView, nodeView(parent)); 
    }
}
