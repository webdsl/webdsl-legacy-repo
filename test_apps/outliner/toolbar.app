module toolbar


define no-span toolbar(doc: Document) {
  block[class:= [border, toolbar]] {
    block[class:= maintitle] { "Outliner" }
    block[class:= toolbarinner] {
      twoColumns() with {
        left() {
          navigate[onclick:=newac()]  { image("/images/new.png") }
          navigate[onclick:=openac()] { image("/images/open.png") }
          navigate[onclick:=remac()]  { image("/images/remove.png") }
          navigate[onclick:=refresh()]{ image("/images/refresh.png") }
        }
        right() {    
          block[class:= toolbarright ] {
            "currentdoc: "
            container[id:= docname] {
              docname(doc)
            }
            navigate[onclick:=closeac()]  { image("/images/close.png") }
            placeholder docdetails {}
          }
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
  action refresh() {
    return outliner(doc);
  }
}

define docdetails(doc: Document) {
  container[class:= [rounded, border, docdetailspopup]] {
    outputString(doc.description)
    navigate[onclick:= action { clear(docdetails); }]{"x"}
  }
}

define no-span docname(doc: Document) {
  container[
    onmouseover := action { replace(docdetails, docdetails(doc)); }
  ]{
    form {
      inplaceFieldEdit(doc.name)
      container[class:=hidden] {
        action("OK",action{replace(docname, docname(doc));})[id:= submit]
      }
    }
  }
}
