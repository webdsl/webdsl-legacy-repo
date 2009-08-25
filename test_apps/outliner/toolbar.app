module toolbar

section templates

define toolbar(doc: Document) {
  action("new", newac())
  action("open", openac())
  //action("save", saveac())
  "currentdoc: "
  container[id:= docname] {
    docname(doc)
  }
  action("x", closeac())
  
  action newac() {
    replace(popup, new_popup);
    append(popupcontents, new_form);
  }
  action openac() {
    replace(popup, open_popup);
  }
  action closeac() {
    return root();
  }
}

define docname(doc: Document) {
  container[
    onclick := action{ replace(docname, editdocname(doc)); },
    onmouseover := action { append(docname, template { output(doc.description) }); },
    onmouseout := action{ replace(docname, docname(doc)); }
  ]{
    output(doc.name)
  } 		
}

define editdocname(doc: Document) {
  form {
    input(doc.name)
    action("OK", saveac())
  }
  action saveac() {
    replace(docname, docname(doc)); 
  }
}
