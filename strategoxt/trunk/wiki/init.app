module wiki-init

section globals

  globals {
  
    var zef : User := 
      User {
        username   := "ZefHemel"
        password   := "secret"
        confirmed  := true
        groups     := {allGroup}        
      };

    var eelco : User := 
      User {
        username   := "EelcoVisser"
        password   := "foo"
        confirmed  := true
        groups     := {adminGroup, webCreateGroup, allGroup}
      };
      
  }
