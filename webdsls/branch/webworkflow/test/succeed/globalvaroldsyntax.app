application test

section datamodel

  entity User{
    username :: String
  }

  globals {
    var bob : User := User { username := "Bob" };
  }
  
  define main() 
  {
    body()
  }
  
  define page home(){
    main()
    
    define body()
    {
      output(bob.username)
    }
   }