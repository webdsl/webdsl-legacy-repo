module json

section pages

define no-span page documentoutline(d: Document) {
"{	identifier: 'id',	label: 'name', 	items: [ "
  nodeoutline(d.root as HeaderNode)
" ] }"
}

define no-span template nodeoutline(n: HeaderNode) {
  mimetype("text/plain") 
  "{ id: '" output(n.id.toString()) "', name: '" output(n.caption) "'" 
    ", children: ["
      for(c: TreeItem in n.children) {
        nodeoutline(c as HeaderNode) 
      }	separated-by { "," }
    "]"
  "}"
}