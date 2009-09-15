module toolbar


define no-span toolbar(doc: Document) {
  block[class:= [border, toolbar]] {
    block[class:= maintitle] { "Outliner" }
    block[class:= toolbarinner] {
      twoColumns() with {
        left() {
          rndButton("New",    true)[onclick:=newac()]
          rndButton("Open",   true)[onclick:=openac()]  
          rndButton("Remove", true)[onclick:=remac()]
          rndButton("Refresh",true)[onclick:=refresh()]
        }
        right() {    
          block[class:= toolbarright ] {
            "currentdoc: "
            container[id:= docname] {
              docname(doc)
            }
            rndButton("Close",false)[onclick:=closeac()]  { image("/images/close.png") }
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
    rndButton("Close",false)[onclick:= action { clear(docdetails); }]
  }
}

define no-span docname(doc: Document) {
  container[onmouseover := action { replace(docdetails, docdetails(doc)); } ] {
    inplaceFieldEdit(doc.name)[onblur:= changeName(null)]
  }
  action changeName(value: String) {
    doc.name := value;
    replace(docname, docname(doc));
  }
}
