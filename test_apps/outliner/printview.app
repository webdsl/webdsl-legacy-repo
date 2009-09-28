module printview

section templates

//not the least trivial solution, but it demonstrates the power of the with/require construction
define printpreview(doc: Document) {
  <b> output(doc.name) </b>
  break
  output(doc.description)
  spacer
  webdslTree(doc.root) with { 
    treeView(item: TreeItem) {
      if (item isa HeaderNode) {
        <b>
          output((item as HeaderNode).caption)
        </b>
      }
      if (item isa TextNode) {
        output((item as TextNode).contents)
      }
      if (item isa ImageNode) {
        output((item as ImageNode).image)
      }
    }
  }
}