module init

section initializing data

  globals {
    var danny : User := 
      User {
        username   := "Danny"
        password   := "123"
        //viewAccess := priv
        friends    := {zef,eelco,lennart,sander,jippe}
      };

    var zef : User := 
      User {
        username   := "Zef"
        password   := "123"
        //viewAccess := fri
        friends    := {danny}
      };
      
    var eelco : User := 
      User {
        username   := "Eelco"
        password   := "123"
        //viewAccess := pub
        friends    := {danny}
      };
 
    var lennart : User := 
      User {
        username   := "Lennart"
        password   := "123"
        //viewAccess := pub
        friends    := {danny}
      };

    var sander : User := 
      User {
        username   := "Sander"
        password   := "123"
        //viewAccess := pub
        friends    := {danny}
      };

    var jippe : User := 
      User {
        username   := "Jippe"
        password   := "123"
        //viewAccess := pub
        friends    := {danny}
      };  
     
    var aPage : MemberPage := 
      MemberPage {
        owner := danny
        content := "This is Danny's page."  
      };
      
    var bPage : MemberPage := 
      MemberPage {
        owner := zef
        content := "This is Zef's page."
      };
      
    var cPage : MemberPage := 
      MemberPage {
        owner := eelco
        content := "This is Eelco's page."
      };
      
     var dPage : MemberPage := 
       MemberPage {
         owner := lennart
         content := "This is Lennart's page."
       };
       
     var ePage : MemberPage := 
       MemberPage {
         owner := sander
         content := "This is Sander's page."
       };
       
     var fPage : MemberPage := 
       MemberPage {
         owner := jippe
         content := "This is Jippe's page."
       };
  }
        