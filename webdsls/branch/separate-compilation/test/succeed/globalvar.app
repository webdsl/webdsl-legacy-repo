application test

section datamodel

  define body() {
    "default body"
  }
  
  entity User{
    username :: String
  }

  var bob : User := User { username := "Bob" };

  define main() 
  {
    body()
  }
  
  define page root(){
    main()
    
    define body()
    {
      output(bob.username)
      output(global.bob.username)
    }
   }



// inferred:
var globalVar1 := globalVar2;
var globalVar2 := globalVar3;
var globalVar3 := User{};
