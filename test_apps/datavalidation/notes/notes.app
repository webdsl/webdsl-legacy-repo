module notes

  entity Note
  {
    name 	    :: String (validate(name.length() > 0,"Name cannot be empty"))
    urgent 		:: Bool
    details 	:: Text
    finished 	:: Bool
    folder		-> Folder (inverse = Folder.notes)
  }

  entity Folder
  {
    name 		:: String
    description :: Text
    notes 	    -> List<Note>  
  }

  extend entity User{
    notes -> List<Note>
    folders -> List<Folder>
  }
  
  define page editNote(n:Note) {
    form{
      formgroup("Note")[labelWidth := globalSettings.labelWidth]{
        label("Name") { input(n.name) } 
        label("Urgent") { input(n.urgent) }
        label("Details") { input(n.details) }
        label("Finished") { input(n.finished) }
        label("Folder") { input(n.folder) }
        block()[style := globalSettings.formButtonsStyle]{
          action("Save",save())
          navigatebutton(note(n),"Cancel")
        }
      }
    }
    action save(){
      n.save();
      return note(n);
    }
  }
  
  define page note(n:Note) {
    formgroup(n.name)[labelWidth := globalSettings.labelWidth]{ 
      label("Urgent") { output(n.urgent) }
      label("Details") { output(n.details) }
      label("Finished") { output(n.finished) }
      label("Folder") { output(n.folder) }
      block()[style := globalSettings.formButtonsStyle]{
        navigate(editNote(n)){"edit"}
      }
    }    
  }
  
  define page editFolder(f:Folder) {
    form{
      formgroup("Folder")[labelWidth := globalSettings.labelWidth]{
        label("Name") { input(f.name) }
        label("Description") { input(f.description) }
        label("Notes") { input(f.notes) }
        block()[style := globalSettings.formButtonsStyle]{
          action("Save",save())
          navigatebutton(folder(f),"Cancel")
        }
      }
    }
    action save(){
      f.save();
      return folder(f);
    }
  }
  
  define page folder(f:Folder) {
    formgroup(f.name)[labelWidth := globalSettings.labelWidth]{ 
      label("Description") { output(f.description) }
      label("Notes") { output(f.notes) }
      block()[style := globalSettings.formButtonsStyle]{
        navigate(editFolder(f)){"edit"}
      }
    }    
  }