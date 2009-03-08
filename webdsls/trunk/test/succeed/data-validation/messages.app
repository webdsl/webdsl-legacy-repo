application messages

  entity User{
    name :: String
  }
  var u : User := User{ name := "bob" };
  var u1 : User := User{ name := "alice" };
  
  define page home(){
    for(u:User) {
      output(u.name)
    }
    
    navigate(somepage()){"link"}
    
    form {
      input(u.name)
      action("save",save())
    }
    action save() {
      u.save();
      message("user name changed to "+u.name);
      return somepage();
    }
  }
  
  define page somepage(){
    "somepage"  
  }
