application mynote

imports init
imports data
imports style
imports layout
imports static

description {
  "manage your own notes!"
}

section pages


  define quickadd() {
    var newnote : Note := Note { details := "enter a new node here" }
    form {
      input(newnote.details)
    }  	
  }

  define quicksearch() {
    var search : String
    form {
      input(search)
    }  	
  }

  define main() {
    folders()
    notes()
    details()
  }
  
  define folders() {
    block{
      list {
        for(f : Folder)	{
          listitem [
              onmouseover := @replace folderdetails << @ \ block {output(f.description)} ,
              onmouseout  := @replace folderdetails << @ \ block{""}
          ]{ output(f.name) }
        }
      }
    }
    group("Folder details") {
      block[id := "folderdetails"] { "" }
    }  
  }
  
  define notes()	{
    "please select a folder"
  }
  
  define details()	{
    "please select a note"
  }
  
  

