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

function getDocument(item: TreeItem) : Document {
  var c: TreeItem := item;
  while (c.parent != null) {
    c := c.parent;
  }
  //HQL query didn't seem to work
  var d: Document := null;
  for(d2: Document) {
    if (d2.root.id == c.id) {
      d := d2;
    }
  }
  return d;
}


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
//  form {
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
      replace(viewHeader, viewHeader(parent)); 
      replace(documentTree, documentTree(getDocument(parent)));
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
//  } 
}
