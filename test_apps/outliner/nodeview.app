module nodeview

section templates

define viewHeader(item: HeaderNode) {
  output(item.caption)
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