module header

section templates


define no-span viewHeader(item: HeaderNode) {
 block[id:= viewHeader, class:=[scopediv, viewHeader]] {
  collapsePanel(false) with {
    caption() {
      inplaceFieldEdit(item.caption)[onblur := savehdr(item, null)]
    }
    contents() {
      dndSource(item.id.toString(), true)
        [ondrop:= dropelement(null,null,null), style:= "min-height: 10px"] 
      {
        for(child : TreeItem in item.children) {
          dndItem(child.id.toString()) {
            twoColumns[left:="10px"] with {
              left() {
                dndHandle() { image("/images/hand.png")[height := "16px"] } 
                if (child isa HeaderNode) {
                  break
                  image("/images/system-search.png")[onclick:=zoomac(child as HeaderNode),height := "16px"]
                }
                break
                image("/images/remove-small.png")[onclick:=remac(child),height := "16px"]
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
 } 
  action savehdr(item: HeaderNode, value: String) {
    item.caption := value;
    replace(statusBar, template{ "Saved header " output(value) });
  }
  action remac(child: TreeItem) {
    var p := child.parent;
    child.parent.children.remove(child);
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
      //itemNode.parent := null;
      itemNode.parent.children.remove(itemNode);
      itemNode.parent := targetNode;
      targetNode.children.insert(index.parseInt(), itemNode);
      
      //update UI
      replace(documentTree, documentTree(getDocument(targetNode)));
      replace(statusBar, template{ "Move action persisted" });
    }
    else {
      replace(statusBar, template{ "Could not persist move action; it would create recursion in the tree" });
    }
  }
}
