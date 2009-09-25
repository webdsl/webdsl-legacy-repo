application mytodo

imports init
imports data
imports static

description {
  "manage your own notes!"
}

section pages

  define quicksearch() {
    var search : String := "";
    form {
      "quicksearch: " 
      input(search)[
        onkeyup := updatesearch(search)
      ]
      image("/images/clear.png")[onclick := action {replace(notelist, template{ "cleared" } ); }]
    }  	
    action updatesearch(val: String) {
      clear(notelist);
      append(notelist, template { "searched " output(val) spacer });
      for(n : Note order by n.name) {
        if (n.name.contains(val))	{
          append (notelist,	displayNote(n));
        }
      }
    }
  }

  define quicksearchitem(n: Note) {
    block { output(n.name) output(n.details) }
  }
  
  define folders() {
    action doEdit(f: Folder) {
      replace (notelist ,editFolder(f));
    }
    list {
      for(f : Folder  order by f.name)	{
        listitem { 
          image("/images/notes.png") 
          navigate()[
            onmouseover := action {replace(folderdetails, template { block {output(f.description) }});},
            onmouseout  := action {replace(folderdetails, template { block { par { "" } }});} ,
            onclick			:= action {replace(notelist, 		  foldercontents(f));}
          ] { output(f.name) }
          //TEST purposes only:
/*          
          " | "
          actionLink(f.name)[
            onmouseover := action {replace(folderdetails,  template { block {output(f.description) }});},
            onmouseout  := action {replace( folderdetails, template { block { par { "" } }});} ,
            onclick			:= action {replace( notelist, 		 foldercontents(f));}
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
      replace( notelist , editFolder(newfolder));
    }
    spacer
    placeholder folderdetails {}    
  }
  
define foldercontents(f : Folder) {
  output(f.name)
  var newNote : Note := Note{}
  form {
    input(newNote.name)
    image("/images/add.png")[onclick := addnote()]
  }
  action addnote() {
    newNote.folder := f;
    newNote.save();
    append (foldernotes , displayNote(newNote));  		
  }
  spacer
  text ( "current notes" )
  block[id := foldernotes] {
    for(note : Note in f.notes  order by note.name) {
        displayNote(note)
    }     
  }
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
      replace (notelist , foldercontents(f));
    }
    action save() {
//      f.save();
      replace (folderlist ,folders());
      replace (notelist , foldercontents(f));
    }
    action remove() {
      f.delete();
      replace (folderlist, folders());
      replace (notelist, template { block{ "select a folder.."}});
    }
  }
}
  
define displayNote(n: Note) {
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
          [onclick:= action { replace (displayNote , editNote(n));}] 
        image("/images/remove.png")[onclick:= remove()]
      } }
    } 
  }
  action finish() {
    n.finished := true;
    n.save();
    replace (this , displayNote(n));
  }
  action remove() {
    var f: Folder := n.folder;
    f.notes.remove(n);
    n.delete();
    clear(this);
  }
}
  
define editNote(n: Note) {
  group("editing note "+n.name) {
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
      replace(this, displayNote(n));
    }
    action save() {
      n.save();
      replace(this, displayNote(n)); 
    }
    action remove() {
      var f: Folder := n.folder;
      f.notes.remove(n);
      n.delete();
      clear(this);
    }
  }
}
