module documentmanagement

section data

entity Document {
  name :: String
  description :: Text
  root <> HeaderNode 
}

function getDocument(item: TreeItem) : Document {
  var c: TreeItem := item;
  while (c.parent != null) {
    c := c.parent;
  }
  //HQL query didn't seem to work
  var d: Document := null;
  for(d2: Document) {
    if (d2.root.id == c.id) {
      d := d2;
    }
  }
  return d;
}

section templates


define new_popup() {
  popup("New outline document")  {
    var n : String := "<new outliner>"
    var d   : Text := "enter description here..."
    form {
      "enter the name of the new document: "
      input(n) 
      break
      "description: "
      input(d)
      action("OK",saveac(n,d))

      action saveac(name: String, description: String) {
        var r: HeaderNode := HeaderNode{ 
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
        relocate(outliner(d));
      }	
    }
  }
}

define open_popup() {
  popup("Open outline document") {
    table [height:= "200px"]{
      row {
        column[width := "400px"] {
          for(d : Document order by d.name) {
            navigate()[
              onclick := action { relocate(outliner(d)); },
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
      action("Yes",delete())
      action("No", action{ clear(popup); })
      action delete() { 
        doc.delete(); 
        relocate(root());
      }
    }
  }
}