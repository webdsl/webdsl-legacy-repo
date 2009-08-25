application outliner

imports data
imports popup
imports documentmanagement
imports toolbar

section pages

define page root() {
  var d: Document := null;
  outliner_contents(d)
}

define page outliner(doc: Document) {
  outliner_contents(doc)
}

define outliner_contents(doc: Document) {
  //a hook for the popup windows
  placeholder popup {}
  
  container[id:= header] {
    "outliner"
    toolbar(doc)
  }
  navigate()[onclick := action { visibility (header , toggle); }]{ "^^^" }

  main(doc)
}

define main(doc: Document) {
  if (doc == null) {
    "No document selected" 
  }
} 
