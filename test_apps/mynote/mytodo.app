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
    for(n : ToDo order by n.name) {
      if (n.name.contains(val))	{
        append (todolist,	displayToDo(n));
      }
    }
  }
}

define folders() {
  action doEdit(f: Folder) {
    replace (todolist ,editFolder(f));
  }
  list {
    for(f : Folder  order by f.name)	{
      listitem { 
        image("/images/todos.png") 
        navigate()[
          onmouseover := action {replace(folderdetails, template { block {output(f.description) }});},
          onmouseout  := action {replace(folderdetails, template { block { par { "" } }});} ,
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
  image("/images/add.png")[onclick:= createfolder()]
  action createfolder() {
    var newfolder: Folder := Folder{};
    newfolder.save();
    replace( todolist , editFolder(newfolder));
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
      image("/images/toggle.png")[onclick:= close()]
      action("remove", remove())
      action("save", save())
    }
    action close() {
      replace (todolist , foldercontents(f));
    }
    action save() {
//      f.save();
      replace (folderlist ,folders());
      replace (todolist , foldercontents(f));
    }
    action remove() {
      f.delete();
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
    newToDo.save();
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
          [onclick:= action { replace (displayToDo , editToDo(n));}] 
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
    var f: Folder := n.folder;
    f.todos.remove(n);
    n.delete();
    clear(this);
  }
}
  
define editToDo(n: ToDo) {
  group("editing todo "+n.name) {
    form {
      table {
        row{"name " input(n.name)			 }
        row{"category" input(n.folder) }
        row{"details" input(n.details) } 
        row{"urgent"	input(n.urgent)  }          		
      }
      navigate[onclick:= close()] { image("/images/toggle.png") }
      action("remove", remove())
      action("save", save())  	
    }
    action close() {
      replace(this, displayToDo(n));
    }
    action save() {
      n.save();
      replace(this, displayToDo(n)); 
    }
    action remove() {
      var f: Folder := n.folder;
      f.todos.remove(n);
      n.delete();
      clear(this);
    }
  }
}
