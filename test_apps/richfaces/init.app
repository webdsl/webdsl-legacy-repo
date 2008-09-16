module init

section inits

  globals{
    var jippe : User := User{
      username := "jippe"
      password := "secret" 
      firstname := "jippe" 
      email := "j@tudelft.nl"  
    };
    var danny : User := User{
      username := "danny"
      password := "secret"  
      firstname := "danny"
      email := "d@tudelft.nl"    
    };
  }

section initdb

define page initDatabase() {

  form { action("Init Database", initDB()) }
   
  action initDB() {

    var User1 : User :=
      User {
        firstname := "Piet"
        lastname := "Pietsma"
        password := "bladiebla"
        username := "pietp"
        email := "pietp@webdsl.org"
      };
    
    var User2 : User :=
      User {
        firstname := "Jan"
        lastname := "Jansen"
        password := "bladiebla"
        username := "janj"
        email := "janj@webdsl.org"
      };
    
    var User3 : User :=
      User {
        firstname := "Paul"
        lastname := "Paulusma"
        password := "bladiebla"
        username := "paulp"
        email := "paulp@webdsl.org"
      };
    
    var User4 : User :=
      User {
        firstname := "Berend"
        lastname := "Boer"
        password := "bladiebla"
        username := "berendb"
        email := "berendb@webdsl.org"
      };
      
    
    User1.persist();
    User2.persist();
    User3.persist();
    User4.persist();
    
    var Publication1 : Publication :=
      Publication {
        title := "Publication number 1"
//        authors := {User1, User4, User3}
      };
    
    var Publication2 : Publication :=
      Publication {
        title := "Publication number 2"
//        authors := {User1, User2, User3, User4}
      };
    
    var Publication3 : Publication :=
      Publication {
        title := "Publication number 3"
//        authors := {User3, User2}
      };
    
    Publication1.persist();
    Publication2.persist();
    Publication3.persist();
  }
}
