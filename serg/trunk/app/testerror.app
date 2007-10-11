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
  
  define page viewUser(user : User) {
    text(user.fullname)
    text(us.name)
  }