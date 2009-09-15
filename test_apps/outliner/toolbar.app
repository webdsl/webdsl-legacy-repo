module toolbar


define no-span toolbar(doc: Document) {
  block[class:= [border, toolbar]] {
    block[class:= maintitle] { "Outliner" }
    block[class:= toolbarinner] {
      twoColumns() with {
        left() {
          rndButton("New",    true)[onclick:=newac(),  class:= firstbtn]
          rndButton("Open",   true)[onclick:=openac(), class:= middlebtn]  
          rndButton("Remove", true)[onclick:=remac(),  class:= middlebtn]
          rndButton("Refresh",true)[onclick:=refresh(),class:= lastbtn]
        }
        right() {    
          block[class:= toolbarright ] {
            "Current: "
            container[id:= docname, style:="margin-left:6px;font-weight:bold"] {
              docname(doc)
            }
            rndButton("Close",false)[
              onclick:=closeac(), 
              style:="float:none;margin:6px;",
              class:="smallbtn"
            ]  
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

define no-span docdetails(doc: Document) {
  container[class:= [scopediv, docdetails, rounded, border]] {
    rndButton("Close",false)[
      onclick:= action { clear(this); },
      style  := "float: right;",
      class  := "smallbtn"
    ]
    break
    spacer
    form {
      inplaceTextArea(doc.description)[onblur := saveac]
    }
  }
  action saveac() {
    replace(this, docdetails(doc));
  }
}

define no-span docname(doc: Document) {
  container[onmouseover := action { replace(docdetails, docdetails(doc)); } ] {
    inplaceFieldEdit(doc.name)[onblur:= changeName,onclick:=changeName(null)]
  }
  action changeName(value: String) {
    doc.name := value;
    replace(docname, docname(doc));
  }
}
