module documentmanagement

section templates

define new_form() {
  var name : String := "<new outliner>"
  var description   : Text := "enter description here..."
  form {
    "enter the name of the new document: "
    input(name)
    "description: "
    input(description)
    action("OK",saveac())
    action saveac() {
      var r: Node := HeaderNode{ 
        caption:= name, 
        parent := null, 
        children := List<Node>() 
      };
      var d: Document := Document{ 
        name := name,
        description := description,
        root := r
      };
      d.save();
      relocate( outliner(d) );
    }	
  }
}

define new_popup() {
 
  popup("New outline document")  {
    
  }
}

define open_popup() {
  define popupBody() {
    "todo"
  }
  popup("Open outline document")
}