module app/init

section inits.

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