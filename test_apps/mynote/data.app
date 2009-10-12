module data

section base example data

entity ToDo
{
  name 		:: String
  details 	:: Text
  finished 	:: Bool
  urgent 	:: Bool
  folder	-> Folder (inverse = Folder.todos, not null)
  
  function deleteToDo(){
    folder.todos.remove(this);
    currentUser.user.todos.remove(this);
    this.delete(); 
  }
}

entity Folder
{
  name 		  :: String
  description :: String
  todos 	  <> List<ToDo>  
  
  function deleteFolder(){
    currentUser.user.folders.remove(this);
    this.delete(); 
  }
}

// make every session have its own todo list, for demo-ing purposes

session currentUser{
  user -> User
}

entity User{
  todos -> List<ToDo>
  folders -> List<Folder>
}

function login(){
  if(currentUser.user == null){
    currentUser.user := User{};
    currentUser.user.save();
  }
}

function createToDo(t : ToDo){
  t.save();
  currentUser.user.todos.add(t);
}

function getUserToDos() : List<ToDo>{
  return currentUser.user.todos;
}

function createFolder(newFolder : Folder): Folder{
  currentUser.user.folders.add(newFolder);
  newFolder.save();
  return newFolder;
}

function getUserFolders() : List<Folder>{
  return currentUser.user.folders;
}