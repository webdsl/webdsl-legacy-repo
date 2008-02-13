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

  define page init() 
  {
    main()
    define body() {
      form{ action("Initalize", initializeGroups()) }
      action initializeGroups() {
        adminGroup.moderators.add(eelco);
        webCreateGroup.moderators.add(eelco);
        allGroup.moderators.add(eelco);
      }
    }
  }
  
  access control rules {
    
    rules page init() { true }
  
  }