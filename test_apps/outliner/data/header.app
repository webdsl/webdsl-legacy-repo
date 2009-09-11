module header

section templates


define viewHeader(item: HeaderNode) {
  collapsePanel(false) with {
    caption() {
      container[id:= titleloc] {
        headerItemDefaultView(item)
      }
    }
    contents() {
      dndSource(item.id.toString(), true)
        [ondrop:= dropelement(null,null,null), style:= "min-height: 10px"] 
      {
        for(child : TreeItem in item.children) {
          dndItem(child.id.toString()) {
            twoColumns[left:="10px"] with {
              left() {
                dndHandle() { ".." } 
                if (child isa HeaderNode) {
                  break
                  navigate[onclick:=zoomac(child as HeaderNode)]{"o"}
                }
                break
                navigate[onclick:=remac(child)]{"x"}
              }
              right() {
                nodeView(child)
              }
            }
          }
        }
      }
      itemadderhidden(item)
    }
  }
  
  action remac(child: TreeItem) {
    var p := child.parent;
    child.parent := null;
    child.delete();
    replace(nodeView, nodeView(p));
  }
  action zoomac(child: HeaderNode) {
    replace(detailView, detailView(child));
  }
  action dropelement(item: String, target: String, index: String) {
    var targetNode: HeaderNode := loadHeaderNode(UUIDFromString(target));
    var itemNode: TreeItem := loadTreeItem(UUIDFromString(item));
    //check recursion    
    if(canMove(itemNode, targetNode)) {
      //update depth
      if (itemNode isa HeaderNode) {
        var h: HeaderNode := itemNode as HeaderNode;
        h.depth := targetNode.depth + 1;
      }
      //update parent
      itemNode.parent := null;
      targetNode.children.insert(index.parseInt(), itemNode);
      itemNode.parent := targetNode;
      //update UI
      replace(statusBar, template{ "Move action persisted" });
      replace(documentTree, documentTree(targetNode.doc));
    }
    else {
      replace(statusBar, template{ "Could not persist move action; it would create recursion in the tree" });
    }
  }
}


define no-span headerItemEditor(item: HeaderNode) {
  form {
    input(item.caption)[width:="80%", onblur:="this.form.onsubmit()"]
    navigate[id:= submit, style:="display: hidden", onclick:=save(item)]{"[Save]"}
  }
  action save(item: HeaderNode) {
    replace(titleloc, headerItemDefaultView(item));
  }
}


define no-span headerItemDefaultView(item: HeaderNode) {
  container[onclick:=onfocus(item), id := titleloc, style:= "padding-left: 10px;"]{
    output(item.caption)
  }
  action onfocus(item: HeaderNode) {
    replace(titleloc,  headerItemEditor(item));
  }
}

define no-span itemadderhidden(parent: HeaderNode) {
  container[id:= itemadder] {
    action("+")[onclick:= action { replace(itemadder, itemaddervisible(parent)); }]
  }
}

define no-span itemaddervisible(parent: HeaderNode) {
  container[id:= itemadder] {
    action("<")[onclick:= action { replace(itemadder, itemadderhidden(parent)); }]
    itemCreator(parent)
  }
}