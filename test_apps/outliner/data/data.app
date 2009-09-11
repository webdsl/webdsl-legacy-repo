module data

section data

entity Document {
  name :: String
  description :: Text
  root <> HeaderNode 
}

entity HeaderNode : TreeItem {
  caption :: String	
  depth :: Int
  doc -> Document
}

entity TextNode : TreeItem {
  contents :: Text
}

entity ImageNode : TreeItem {
  image :: Image
}

//prevent recursion
function canMove(item: TreeItem, target: HeaderNode): Bool {
  if (item isa HeaderNode) {
    var cur : HeaderNode := target; 
    while(cur.parent != null) {
      cur := cur.parent as HeaderNode;
      if (cur.parent.id.toString() == item.id.toString()) {
        return false;
      }  
    }
  } 
  return true;
}

define itemCreator(parent: HeaderNode) {
  form {
    action("Header", newheaderac(parent))
    action("Text block", newtextac(parent))
    action("Image", newimgac(parent))

    action newheaderac(parent: HeaderNode) {
      var h : HeaderNode := HeaderNode {
        caption := "<double click to enter a new name>",
        depth := parent.depth + 1,
        parent := parent,
        doc := parent.doc
      };
      h.save();
      replace(viewHeader, viewHeader(parent)); 
      replace(documentTree, documentTree(parent.doc));
    }  		
    action newtextac(parent: HeaderNode) {
      var h : TextNode := TextNode {
        contents := "<double click to edit>",
        parent := parent
      };
      h.save();
      replace(viewHeader, viewHeader(parent)); 
    }  		
    action newimgac(parent: HeaderNode) {
      var h : ImageNode := ImageNode {
        parent := parent
      };
      h.save();
      replace(viewHeader, viewHeader(parent)); 
    }  		
  }
}
