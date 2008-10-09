application mynote

imports init
imports data
imports style
//imports layout
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
    }  	
    action updatesearch(val: String) {
      clear notelist << ;
      append notelist << { "searched " output(val) spacer };
      for(n : Note) {
        if (n.name.contains(val))	{
          append notelist <<	displayNote(n);
        }
      }
    }
  }

  define quicksearchitem(n: Note) {
    block { output(n.name) output(n.details) }
  }
  
  define folders() {
    list {
      for(f : Folder)	{
        listitem { 
          image("/images/notes.png") 
          actionLink(f.name)[
            onmouseover := {replace folderdetails << { block {output(f.description) }};},
            onmouseout  := {replace folderdetails << { block { par { "" } }};} ,
            onclick			:= {replace notelist 			<< foldercontents(f);}
          ] 
          actionLink("[edit]")[onclick:= {replace notelist << editFolder(f);}]
        }
      }
    }
    spacer
    actionLink("[add new folder]", createfolder())
    action createfolder() {
      var newfolder: Folder := Folder{};
      replace notelist << editFolder(newfolder);
    }
    spacer
    block[id := folderdetails] { "" }    
  }
  
  define foldercontents(f : Folder) {
    output(f.name)

    var newNote : Note := Note{}
    form {
      input(newNote.name)
      actionLink("quick add",addnote())
    }
    action addnote() {
      newNote.folder := f;
      newNote.save();
      append foldernotes << displayNote(newNote);  		
    }
    
    spacer
    text ( "current notes" )
    
    block[id := foldernotes] {
      for(note : Note in f.notes) {
          displayNote(note)
      }     
    }
  }
  
  define editFolder(f: Folder) {
    group("editing folder "+f.name) {
      actionLink("X", close())
      form {
        table {
          row{"name " input(f.name)			 }
          row{"description" input(f.description) }
        }
        action("remove", remove())
        action("save", save())
      }
      action close() {
        replace notelist << foldercontents(f);
      }
      action save() {
        f.save();
        replace notelist << foldercontents(f);
        replace folderlist << folders();
      }
      action remove() {
        f.delete();
        replace notelist << { block{ "select a folder.."}};
        replace folderlist << folders();
      }
    }
  }
  
  define displayNote(n: Note) {
    group(n.name) {
      if(n.urgent) {
        image("/images/urgent.png")
      }
      if (n.finished) {
        image("/images/finished.png")
      }
      
      output(n.details)
      
      if (n.finished == false)  {
        var b: Bool := false
        input(b)[onclick := finish()] navigate[onclick:= finish()] {"finished"}
      }
      actionLink("[edit]")[onclick:={ replace this << editNote(n);}] 
    }
    action finish() {
      n.finished := true;
      n.save();
      replace this << displayNote(n);
    }
  }
  
  define editNote(n: Note) {
    group("editing note "+n.name) {
      actionLink("X", close())
      form {
        table {
          row{"name " input(n.name)			 }
          row{"category" input(n.folder) }
          row{"details" input(n.details) } 
          row{"urgent"	input(n.urgent)  }          		
        }
        action("save", save())  	
      }
      action close() {
        replace notelist << foldercontents(n.folder);
      }
      action save() {
        n.save();
        replace notelist << foldercontents(n.folder);
      }
    }
  }
