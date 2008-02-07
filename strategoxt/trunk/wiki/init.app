module wiki-init

section globals

  globals {
  
    var zef : User := 
      User {
        username   := "ZefHemel"
        password   := "secret"
        groups     := {allGroup}        
      };

    var eelco : User := 
      User {
        username   := "EelcoVisser"
        password   := "foo"
        groups     := {adminGroup, webCreateGroup, allGroup}
      };
      
  }
