module data

section data

entity Document {
  name :: String
  description :: Text
  root -> Node 
}

entity Node {
  parent -> Node (inverse = Node.children)
  caption :: String	
  children -> List<Node>// (inverse = Node.parent)
}

entity HeaderNode : Node {
}

entity TextNode : Node {
  contents :: Text
}

entity ImageNode : Node {
  image :: Image
}

