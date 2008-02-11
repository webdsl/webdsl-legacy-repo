module init

section globals .

  globals {
    var alice : User := 
      User {
        username   := "Alice"
        password   := "changeme"
        viewAccess := priv
        friends    := {bob}
      };

    var bob : User := 
      User {
        username   := "Bob"
        password   := "changeme"
        viewAccess := fri
        friends    := {alice,charlie}
      };
      
    var charlie : User := 
      User {
        username   := "Charlie"
        password   := "changeme"
        viewAccess := pub
      };
  
    var dave : User := 
      User {
        username   := "Dave"
        password   := "changeme"
        viewAccess := priv
      };  
    
    var alicePage : Page := 
      Page {
        name := "Member page: "+alice.username
        owner := alice
        content := "This is Alice's page."
       
      };
      
    var bobPage : Page := 
      Page {
        name := "Member page: "+bob.username
        owner := bob
        content := "This is Bob's page."
       
      };
    var charliePage : Page := 
      Page {
        name := "Member page: "+charlie.username
        owner := charlie
        content := "This is Charlie's page."
       
      };
     var davePage : Page := 
      Page {
        name := "Member page: "+dave.username
        owner := dave
        content := "This is Dave's page."
       
      };
  }
        