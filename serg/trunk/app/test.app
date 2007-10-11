application org.webdsl.test

section users.

  entity User {
    name :: String 
  }
  
section home.

  define page home() {
    var user : User := User{name := "foo"};
    
    input(user.name)
  }
  
  define main() {
    body()
  }