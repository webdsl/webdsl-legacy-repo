application BaseExample

imports accesscontrol
imports init
imports templates
imports data

description {
  
}

section the pages

  define page home(){
    main()
    define body(){
      section{ "Welcome to the example application which manages users and documents." }
      section{ "The supported operations are creation, viewing, and editing." }
      login()
      logout()
    }
  }


  define page editDocument(document:Document){
    main()
    define body(){
      section{
        header{ "Edit Document " output(document.title) }
        form{
          table{
            row{ "Title:" input(document.title) }
            row{ "Text:" input(document.text) }
            row{ "Author:" input(document.author) }  
            allowedUsersRow(document)
          }
          action("Save", save())
          action("Cancel", cancel())
        }
        action cancel(){
          return viewDocument(document);
        }
        action save(){
          document.save();
          return viewDocument(document);
        }
      }
    }
  }
  
  
  define page viewDocument(document:Document){
    main()
    define body(){
      section{
        header{ output(document.name) }
        table{
          row{ "Title:" output(document.title) }
          row{ "Text:" output(document.text) }
          row{ "Author:" 
               navigate(viewUser(document.author)){
                 output(document.author.name)
               }
          }
        }  
      }
    }
  }
  
   define page viewUser(u:User) {
    main()
    define body() {
      section {
        header {
          output(u.name)
        }
        table {
          row {
            "Name:"
            output(u.name)
          }
          row{
            "Password:"
            output(u.password)
          }
        }  
      }
    }
  }
  
  define page allDocument() {
    main()
    define body() {
      section{
        header{
          "All Document"
        }
        form{
          list{
            for ( document3 : Document where true order by null asc ) {
              listitem(){
                navigate(document(document3)){
                  text(document3.name){
                  }
                }
                " "
                actionLink("[X]", removeDocument(document3)){
                }
                action removeDocument ( document4 : Document )
                {
                  document4.delete();
                }
              }
            }
          }
        }
      }
    }
  }
  
  
  define page createDocument(){
    main()
    define body(){
      var document : Document := Document{};
      section{
        header{ "Create new Document" }
        form{
          table{
            row{ "Title:" input(document.title) }
            row{ "Text:" input(document.text) }
            row{ "Author:" input(document.author) }
          }   
          action("Save", save())
          action("Cancel", cancel())
        }
        action cancel(){
          return home();
        }
        action save(){
          document.save();
          return viewDocument(document);
        }
      }
    }   
  } 