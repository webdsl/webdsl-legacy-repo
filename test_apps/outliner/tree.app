module tree

section data

entity TreeItem {
  parent -> TreeItem (inverse = TreeItem.children)
  children -> List<TreeItem>// (inverse = TreeItem.parent)
}