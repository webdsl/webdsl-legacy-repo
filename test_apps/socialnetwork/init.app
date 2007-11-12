module init

section globals .

  globals {
    var zef : User := 
      User {
        username   := "zef"
        password   := "secret"
        viewAccess := priv
      };

    var danny : User := 
      User {
        username   := "danny"
        password   := "ac"
        viewAccess := priv
      };
      
    var eelco : User := 
      User {
        username   := "eelco"
        password   := "foo"
        viewAccess := priv
      };
    
    var zefPage : Page := 
      Page {
        name := "Member page: "+zef.username
        owner := zef
        content := "This is zefs home page."
       
      };
      
    var dannyPage : Page := 
      Page {
        name := "Member page: "+danny.username
        owner := danny
        content := "This is dannys home page."
       
      };
      
    var eelcoPage : Page := 
      Page {
        name := "Member page: "+eelco.username
        owner := eelco
        content := "This is eelcos home page."
      };
  }
        