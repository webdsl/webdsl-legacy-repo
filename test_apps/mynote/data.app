module data

section base example data

  entity Note
  {
    name 			:: String
    details 	:: String
    finished 	:: Bool
    urgent 		:: Bool
    folder		-> Folder 
  }

  entity Folder
  {
    name 				:: String
    description :: String
    notes 			<> List<Note> (inverse = Note.folder)
  }
