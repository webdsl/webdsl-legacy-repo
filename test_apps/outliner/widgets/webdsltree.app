module webdsltree

section data

entity TreeItem {
  parent -> TreeItem (inverse = TreeItem.children)
  children <> List<TreeItem>// (inverse = TreeItem.parent)
}

section templates

define no-span webdslTree(item: TreeItem) requires treeView(TreeItem){
  block[class:= [scopediv, customTree] 
    /* an onclick action could be defined here ]*/ 
  ]{
    /* here could be some +/- sign responsible for collapsing */
    treeView(item)
  }
  //break
  block[style:="padding-left: 16px"] {
    for(child: TreeItem in item.children) {
      webdslTree(child) 
    }
  }
}