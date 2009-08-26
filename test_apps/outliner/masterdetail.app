module masterdetail

section templates

define template masterdetail(root: TreeItem) requires detailview(TreeItem),treeview(TreeItem)  {
  table {
    row {
      column[width:= '50%'] {
        tree(root) with {
          nodeview(item: TreeItem) {
            ">" 
            container[onclick := action{ replace(masterdetailcontents, detailview(item)); }] {
              treeview(item)
            }
            break
          }
        }
      }
      column[width:= '50%', id:= masterdetailcontents] {
        "(nothing selected)"
      }
    }
  }
}

define template tree(startnode: TreeItem) requires nodeview(TreeItem) {
  nodeview(startnode)
} 
