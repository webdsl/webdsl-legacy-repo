application com.example.guestbook

description {
	This is an automatically generated description
}

section access control

  principal is User with credentials username
  
access control rules

  rule page home() {
      true
  }

  rule page login() {
    true
  }

  rule page register() {
    true
  }

  rule page error(*) {
    true
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
              return home();
            }
          }
          securityContext.loggedIn := false;
          return error("Wrong combination of username and password");
        }
      }
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

section logic

function doSomethingUseful() {
  var e : Entry := Entry{};
  e.message := "Hello!";
  e.sender := createUser("Frits2");
  e.persist();
}

function createUser(username : String) : User {
  var u : User := User{ username := username };
  u.persist();
  return u;
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
    navigate(login()) { "Login" }
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

define print(s : String) {
  output(s)
}
define print(i : Int) {
  output(i)
}
section pages

define page home() {
    title{"Guestbook"}
    main()
    define body() {
      table {
          header { "Sender" "Entry" "Action" }
          for(m : Entry order by m.date asc) {
              row { output(m.sender.username) output(m.message) navigate(editEntry(m)) { "Edit" }}
          }
      }
      var newEntry : Entry := Entry{};
      section {
        section { 
          header{ "Add entry" } 
          addEntryTemplate(newEntry)
        }
      }
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
      user.password := user.password.digest();
      user.save();
      return home();
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
      row { "Sender: " input(m.sender) }
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
    form {
      action("Add", save())
      table {
        row { "Message: " input(m.message) }
      }
    }
    action save() {
      m.sender := securityContext.principal;
      m.save();
      return home();
    }
  }
}

define page editAll() {
    main()
    define body() {
      form {
        table {
          for(m : Entry) {
            row { output(m.sender) input(m.message) }
          }
        }
        action("Save", save())
      }
      action save() {
        return home();
      }
    }
}

define page user(u : User) {
    "User"
}
