module init

section initialization

  globals {
    var user1 : User := User { 
      username := "zef",
      password := "secret", 
      email := "zef@zefhemel.com",
      roles := { adminRole, moderatorRole }
     };
  }
