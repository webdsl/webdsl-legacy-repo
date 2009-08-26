module data

section data

entity Document {
  name :: String
  description :: Text
  root -> TreeItem 
}

entity HeaderNode : TreeItem {
  caption :: String	
}

entity TextNode : TreeItem {
  contents :: Text
}

entity ImageNode : TreeItem {
  image :: Image
}

