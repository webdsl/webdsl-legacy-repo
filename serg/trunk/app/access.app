module app/access

section login

  define page login() { 
    main()
    
    define sidebar(){}
    
    var user : User;
        
    define body() { 
      form {
        table {
          row{"username" input(user.username)}
          row{"password" input(user.password)}
        }
        action("Login", login())
      }
    }

    action login() { 
      //var users : List<User>; // := user.search();
      //if true then // users.size() == 1 then
      //  session.user := user;
      //  return home();
      //else
      //  errorMessage("Wrong username/password combination");
      //end
      return home();
    }
  
  }
