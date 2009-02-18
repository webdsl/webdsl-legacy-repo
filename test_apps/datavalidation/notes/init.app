module init
  
  init{
    var f1 := Folder {
      name := "Conferences"
      description := "Everything related to conferences." 
    };
    f1.save();
  
    var note1 := Note {
    name 	    := "Submit tutorial ICWE09"
    urgent 		:= true
    details 	:= "Don't forget to submit tutorial to ICWE09."
    finished 	:= true
    folder := f1
    };
    
    note1.save();
    
    

  }
