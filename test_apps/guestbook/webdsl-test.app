application com.example.guestbook

description {
	This is an automatically generated description
}

section access control

  principal is User with credentials username
  
access control rules

  rule page *(*) {
      true
  }

  rule page editEntry(e : Entry) {
      e.sender = securityContext.principal
  }

section ac login stuff

  define page login() {
    main()
    title{"Log in"}
    define body() {
      var username : String;
      var password : Secret;
      form { 
        table {
          row{ "Username: " input(username) }
          row{ "Password: " input(password) }
          row{ action("Sign in", signin()) "" }
        }
        action signin() {
          for (us : User where us.username = username) {
            if (us.password.check(password)) {
              securityContext.principal := us;
              securityContext.loggedIn := true;
              return message("You have been logged in.");
            }
          }
          securityContext.loggedIn := false;
          return error("Wrong combination of username and password");
        }
      }
    }
  }

  define page logout() {
    init {
      securityContext.loggedIn := false;
      securityContext.principal := null;
      goto home();
    }
  }

  define page error(msg : String) {
    title("Error!")
    main()
    define body() {
      header{"Error!"}
      output(msg)
    }
  }

  define page message(msg : String) {
    title("Message")
    main()
    define body() {
      header{"Friendly Message"}
      output(msg)
    }
  }

section data model

entity User {
  username :: String(id, name, inline)
  password :: Secret
  entries -> Set<Entry> (inverse=Entry.sender)
}

entity Entry {
  sender   -> User
  date     :: DateTime
  message  :: Text
}

section templates

define main() {
    head()
    body()
    foot()
}
define head() {
    header { "Wiki Guestbook" }
    navigate(home()) { "Home" }
    " | "
    if(!securityContext.loggedIn) {
      navigate(login()) { "Login" }
    }
    if(securityContext.loggedIn) {
      navigate(logout()) { "Logout" }
    }
    " | "
    navigate(register()) { "Register" }
    horizontalspacer
}
define foot() {
    horizontalspacer
    par {"Powered by"}
    image("http://webdsl.org/webdslorg/images/WebDSL-small.png") 
}

define body() {
    "Nothin"
}

section pages

define page home() {
    title{"Guestbook"}
    main()
    define body() {
      var p : Int := 0;
      table {
          header { "Sender" "Entry" "Action" }
          for(m : Entry order by m.date desc limit 10 offset 10*p) {
              row { output(m.sender.username) output(m.message) navigate(editEntry(m)) { "Edit" }}
          }
          form {
            hidden(p)
            if(p > 0) {
              action("previous", previous())
            }
            action("next", next())

            action previous() {
              p := p - 1;
            }
            action next() {
              p := p + 1;
            }
          }
      }
      var newEntry : Entry := Entry{};
      addEntryTemplate(newEntry)
    }
}

define page register() {
  title {"Register"}
  main()
  define body() {
    var user : User := User{};
    form {
      par { "Username: " input(user.username) }
      par { "Password: " input(user.password) }
      action("Register", register())
    }
    action register() {
      for(u : User where u.username = user.username) {
        return error("User already registered. Sorry.");
      }
      user.password := user.password.digest();
      user.save();
      return message("You have no been registered. Click Login to login.");
    }
  }
}

define page editEntry(m : Entry) {
    main()
    define body() {
        editEntryTemplate(m)
    }
}

define editEntryTemplate(m : Entry) {
  form {
    table {
      row { "Message: " input(m.message) }
    }
    action("Save", save())
    action("Delete", delete())
  }
  action save() {
    m.save();
    return home();
  }
  action delete() {
    m.delete();
    return home();
  }
}

define addEntryTemplate(m : Entry) {
  if(securityContext.loggedIn) {
    section {
      section { 
        header{ "Add entry" } 
        form {
          table {
            row { "Message: " input(m.message) }
          }
          action("Add", save())
        }
        action save() {
          m.sender := securityContext.principal;
          m.save();
          return home();
        }
      }
    }
  }
}

define page user(u : User) {
    "User"
}
