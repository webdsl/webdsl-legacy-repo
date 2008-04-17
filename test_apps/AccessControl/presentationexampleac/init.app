module init

section initializing data

  globals {
    var danny : User := 
      User {
        username   := "Danny"
        password   := "123"
        friends    := {zef,eelco,lennart,sander,jippe}
      };

    var zef : User := 
      User {
        username   := "Zef"
        password   := "123"
        friends    := {danny}
      };
      
    var eelco : User := 
      User {
        username   := "Eelco"
        password   := "123"
        friends    := {danny}
      };
 
    var lennart : User := 
      User {
        username   := "Lennart"
        password   := "123"
        friends    := {danny}
      };

    var sander : User := 
      User {
        username   := "Sander"
        password   := "123"
        friends    := {danny}
      };

    var jippe : User := 
      User {
        username   := "Jippe"
        password   := "123"
        friends    := {danny}
      };
      
    var bob : User := 
      User {
        username   := "Bob"
        password   := "123"
      };    
    
     var gPage : MemberPage := 
       MemberPage {
         owner := bob
         content := "This is Bob's page."
         viewAccess := priv
       };
     
    var aPage : MemberPage := 
      MemberPage {
        owner := danny
        content := "This is Danny's page."
        viewAccess := pub 
      };
      
    var bPage : MemberPage := 
      MemberPage {
        owner := zef
        content := "This is Zef's page."
        viewAccess := fri
      };
      
    var cPage : MemberPage := 
      MemberPage {
        owner := eelco
        content := "This is Eelco's page."
        viewAccess := fri
      };
      
     var dPage : MemberPage := 
       MemberPage {
         owner := lennart
         content := "This is Lennart's page."
         viewAccess := fri
       };
       
     var ePage : MemberPage := 
       MemberPage {
         owner := sander
         content := "This is Sander's page."
         viewAccess := fri
       };
       
     var fPage : MemberPage := 
       MemberPage {
         owner := jippe
         content := "This is Jippe's page."
         viewAccess := fri
       };
  }
        