module documentmanagement

section templates


define new_popup() {
  popup("New outline document")  {
    var n : String := "<new outliner>"
    var d   : Text := "enter description here..."
    form {
      "enter the name of the new document: "
      input(n)
      "description: "
      input(d)
      action("OK",saveac(n,d))

      action saveac(name: String, description: String) {
        var r: TreeItem := HeaderNode{ 
          caption:= name, 
          //children := List<TreeItem>(), 
          parent := null ,
          depth := 1
        };
        var d: Document := Document{ 
          name := name,
          description := description,
          root := r
        };
        d.save();
        return outliner(d) ;
      }	
    }
  }
}

define open_popup() {
  popup("Open outline document") {
    table {
      row {
        column[width := 400px] {
          for(d : Document order by d.name) {
            navigate()[
              onclick := action { return outliner(d); },
              onmouseover := action { replace(opendocdetails, template { output(d.description) }); },
              onmouseout  := action { clear(opendocdetails); }			    	
            ]{ output(d.name) }
            break
          }
        }
        column[width := 200px] {
          group("Document details") { placeholder opendocdetails {} }
        }
      }
    }		
  }
}

define delete_popup(doc: Document) {
  popup("Remove document") {
    form {
      "Delete document '" output(doc.name) "' ? Press 'yes' to confirm. "
      action("Yes",action{ doc.delete(); return root(); })
      action("No", action{ clear(popup); })
    }
  }
}