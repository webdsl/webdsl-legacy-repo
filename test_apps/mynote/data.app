module data

section base example data

  entity Note
  {
    name 			:: String
    details 	:: Text
    finished 	:: Bool
    urgent 		:: Bool
    folder		-> Folder (inverse = Folder.notes)
  }

  entity Folder
  {
    name 				:: String
    description :: String
    notes 			<> List<Note>  
  }
