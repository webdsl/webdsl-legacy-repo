application mytodo

imports init
imports data
imports static

description {
  "manage your own todos!"
}

section pages

define quicksearch() {
  var search : String := "";
  form {
    "quicksearch: " 
    input(search)[onkeyup := updatesearch(search)]
    image("/images/clear.png")[onclick := action {replace(todolist, template{ "cleared" } ); }]
  }  	
  action updatesearch(val: String) {
    clear(todolist);
    append(todolist, template { "searched " output(val) spacer });
    for(n : ToDo in getUserToDos() order by n.name) {
      if (n.name.contains(val))	{
        append (todolist,	displayToDo(n));
      }
    }
  }
}

define showFolderQuick(f:Folder){
  block {output(f.description) }
  spacer 
  for(t:ToDo in f.todos){ 
    block{
      output(t.finished)
      output(t.name)
    } 
  }
}

define folders() {
  action doEdit(f: Folder) {
    replace (todolist ,editFolder(f));
  }
  list {
    for(f : Folder in getUserFolders() order by f.name)	{
      listitem { 
        image("/images/todos.png") 
        navigate()[
          onmouseover := action {replace(folderdetails, showFolderQuick(f));},
          //onmouseout  := action {replace(folderdetails, template { block { par { "" } }});} ,
          onclick			:= action {replace(todolist, 		  foldercontents(f));}
        ] { output(f.name) }
        //TEST purposes only:
/*          
        " | "
        actionLink(f.name)[
          onmouseover := action {replace(folderdetails,  template { block {output(f.description) }});},
          onmouseout  := action {replace( folderdetails, template { block { par { "" } }});} ,
          onclick			:= action {replace( todolist, 		 foldercontents(f));}
        ] 

        //TEST:
        action("[Edit]")[onclick:=doEdit(f)]
        action("[Edit]", doEdit(f))
*/
        image("/images/edit.png")[onclick := doEdit(f)]          
        
      }
    }
  }
  spacer
  var newFolder : Folder := Folder{}
  form {
    input(newFolder.name)
    image("/images/add.png")[onclick := create()]
  }
  action create() {
    createFolder(newFolder);
    replace (folderlist ,folders());
    replace (todolist , foldercontents(newFolder));
  }
  spacer
  placeholder folderdetails {}    
}
  
define editFolder(f: Folder) {
  group("editing folder "+f.name) {
    form {
      table {
        row{"name " input(f.name)			 }
        row{"description" input(f.description) }
      }
      action("remove", remove())
      action("save", save())
    }
    image("/images/toggle.png")[onclick:= close()]
    action close() {
      replace (todolist , foldercontents(f));
    }
    action save() {
      replace (folderlist ,folders());
      replace (todolist , foldercontents(f));
    }
    action remove() {
      f.deleteFolder();
      // a flush is necessary here, since the delete is postponed  
      // (all changes to the db happen at the end of the page request or when 'flush()' is called)
      // which causes the render of 'folders()' to include the deleted item      
      flush();
      replace (folderlist, folders());
      replace (todolist, template { block{ "select a folder.."}});
    }
  }
}
  
define foldercontents(f : Folder) {
  output(f.name)
  var newToDo : ToDo := ToDo{}
  form {
    input(newToDo.name)
    image("/images/add.png")[onclick := addtodo()]
  }
  action addtodo() {
    newToDo.folder := f;
    createToDo(newToDo);
    append (foldertodos , displayToDo(newToDo));  		
  }
  spacer
  text ( "current todos" )
  block[id := foldertodos] {
    for(todo : ToDo in f.todos  order by todo.name) {
        displayToDo(todo)
    }     
  }
}

define displayToDo(n: ToDo) {
  form {
    group(n.name) {
      row { column {
        if(n.urgent) {
          image("/images/urgent.png")
        }
        if (n.finished) {
          image("/images/finished.png")
        }
        output(n.details)
      } }
      if (n.finished == false)  { row { column {
          var b: Bool := false
          input(b)[onclick := finish()] 
          container[onclick:= finish()] {"finished"}
      } } }
      row { column { 
        image("/images/edit.png")
          [onclick:= action { replace (displayToDo , editToDo(n, n.folder));}] 
        image("/images/remove.png")[onclick:= remove()]
      } }
    } 
  }
  action finish() {
    n.finished := true;
    n.save();
    replace (this , displayToDo(n));
  }
  action remove() {
    n.deleteToDo();
    clear(this);
  }
}
  
define editToDo(n: ToDo, f:Folder) {
  group("editing todo "+n.name) {
    form {
      table {
        row{"name " input(n.name)			 }
        row{"category" select(n.folder from getUserFolders()) }
        row{"details" input(n.details) } 
        row{"urgent"	input(n.urgent)  }          		
      }
      action("remove", remove())
      action("save", save())  	
    }
    //navigate is not in the form so the inputs are not submitted here
    navigate[onclick:= action{ replace(this, displayToDo(n)); }] { image("/images/toggle.png") }

    action save() {
      // action could have placed this todo in another folder,
      // in that case remove this from the contents of the current folder
      if(n.folder != f){
        clear(this);
      }
      else{
        replace(this, displayToDo(n));
      }
    }
    action remove() {
      n.deleteToDo();
      clear(this);
    }
  }
}
