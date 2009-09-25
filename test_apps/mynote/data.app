module data

section base example data

entity ToDo
{
  name 			:: String
  details 	:: Text
  finished 	:: Bool
  urgent 		:: Bool
  folder		-> Folder (inverse = Folder.todos)
}

entity Folder
{
  name 				:: String
  description :: String
  todos 			<> List<ToDo>  
}
