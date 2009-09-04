module toolbar


define no-span toolbar(doc: Document) {
  block[ class:= toolbar ] {
    twoColumns() with {
      left() {
        action("new", newac())
        action("open", openac())
        action("rem", remac())
      }
      right() {    
        block[ style:= "width: 100%; text-align: right" ] {
          "currentdoc: "
          container[id:= docname] {
            docname(doc)
          }
          action("x", closeac())
          placeholder docdetails {}
        }
      }
    }  
  }
      
  action newac() {
    replace(popup, new_popup);
  }
  action openac() {
    replace(popup, open_popup);
  }
  action closeac() {
    return root();
  }
  action remac() {
    replace(popup, delete_popup(doc));
  }
}

define docdetails(doc: Document) {
  container[style:="display: block; position: relative; left: 10px; top: 40px; background-color: gray; z-index: 3;"] {
    outputString(doc.description)
  }
}

define docname(doc: Document) {
  container[
    onclick := action{ replace(docname, editdocname(doc)); },
    onmouseout := action{ clear(docdetails); },
    onmouseover := action { replace(docdetails, docdetails(doc)); }
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
