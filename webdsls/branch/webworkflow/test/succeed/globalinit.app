application test

section datamodel

  entity User{
    username :: String
  }

  var bob : User := User { };

  init{
    bob.username := "Bob";
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