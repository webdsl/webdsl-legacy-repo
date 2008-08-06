application com.example.guestbook

description {
	This is an automatically generated description
}

section data model

entity User {
  username :: String(id)
  messages -> Set<Entry> (inverse=Entry.sender)
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
      table {
          header { "Sender" "Entry" "Action" }
          for(m : Entry) {
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
      //"|"
      //navigate(editAll()) { "Edit all" }
    }
}

define page register() {
  title {"Register"}
  main()
  define body() {
    var user : User := User{};
    form {
      par { "Username: " input(user.username) }
      action("Register", register())
    }
    action register() {
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
  form {
    table {
      row { "Sender: " input(m.sender) }
      row { "Message: " input(m.message) }
    }
    action("Add", save())
  }
  action save() {
    m.save();
    return home();
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
