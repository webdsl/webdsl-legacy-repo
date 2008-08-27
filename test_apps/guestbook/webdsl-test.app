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

  rule template *(*) {
      true
  }

  rule action *(*) {
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
    powered()
    foot()
}
define head() {
    header { "Wiki Guestbook" }
    list {
      listitem { navigate(home()) { "Home" } }
      if(!securityContext.loggedIn) {
        listitem { navigate(login()) { "Login" } }
      }
      if(securityContext.loggedIn) {
        listitem { navigate(logout()) { "Logout" } }
      }
      listitem { navigate(register()) { "Register" } }
    }
}
define foot() {
  "(c) Zef Hemel, 2008"
}

define powered() {
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
      //var p : Int := 0;
      table {
          header { "Sender" "Entry" "Action" }
          for(m : Entry order by m.date desc per 10) {
              row { output(m.sender) output(m.message) navigate(editEntry(m)) { "Edit" }}
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
      return message("You have now been registered. Click Login to login.");
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
    title { output(u.username) }
    main()
    define body() {
      header{ output(u.username) "entries" }
      list {
        for(e : Entry in u.entries order by e.date asc per 10) {
          listitem { output(e.message) }
        }
      }
    }
}

layout
  template main() {
    head();
    body();
    footer: [ powered() | foot() ];
    //powered();
    //foot();
  }

style guestBookStyle

  template head() {
    background-color := Color.gray;
    width := main().width;
  }

  template head() >> list() {
    orientation := Orientation.horizontal;
    separator := Separator.pipe;
    separator-space := 10px; // @todo: rename to spacing and set on listitem()
  }

  template body() {
    width := main().width;
  }
  
  template powered() {
    align := Align.left;
  }

  #footer {
    border-top-width := 1px;
    border-top-style := BorderStyle.solid;
    border-top-color := Color.black;
  }

  template foot() {
    align := Align.right;
  }

  template main() {
    align := Align.center;
    width := 800px;
  }
