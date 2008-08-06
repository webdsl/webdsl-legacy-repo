application com.example.basic

description {
	This is an automatically generated description
}

section data model

entity User {
  username :: String(id)
  messages -> Set<Message> (inverse=Message.sender)
  receivedmessages -> Set<Message>
}

entity Message {
  sender   -> User
  recipients -> Set<User> (inverse=User.receivedmessages)
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
    par { image("http://webdsl.org/webdslorg/images/WebDSL-small.png") }
    navigate(home()) { "Home" }
    horizontalspacer
}
define foot() {
    horizontalspacer
    par { "Copyright Zef Hemel, 2008" }
}

define body() {
    "Nothin"
}
section pages

define page home() {
    title{"Home!"}
    main()
    define body() {
      section {
          header{ "Test!" }
          par { "Here you see all messages." }
      }
      table {
          header { "Sender" "Message" "Action" }
          for(m : Message) {
              row { output(m.sender.username) output(m.message) navigate(editMessage(m)) { "Edit" }}
          }
      }
      navigate(home()) { "Home" }
      "|"
      navigate(editAll()) { "Edit all" }
    }
}

define page editMessage(m : Message) {
    main()
    define body() {
      form {
        input(m.message)
        action("Save", save())
      }
      action save() {
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
          for(m : Message) {
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
