application com.example.optimizequery

description {
	This is an automatically generated description
}


  init {
    var u1 : User := User { name := "User 1", age := 25 }; u1.save();
    var u2 : User := User { name := "User 2", age := 22 }; u2.save();
    var m1 : Message := Message { text := "Hello world!", author := u1 }; m1.save();
    var m2 : Message := Message { text := "Hello universe!", author := u2 }; m2.save();
  }

  entity User {
    name :: String
    age  :: Int
    messages -> Set<Message>
  }

  entity Message {
    author -> User (inverse=User.messages)
    text   :: Text
    date   :: DateTime
  }

section pages

  function allMessages() : List<Message> {
    var l : List<Message> := List<Message>();
    for(m : Message) {
      var nm : String := m.author.name;
      l.add(m);
    }
    return l;
  }

  define page home() {
    header { "Welcome!" }
    for(m : Message) {
      par {
        output(m.author.name) ": " output(m.text)
      }
    }
    for(m : Message in allMessages()) {
      "Bla"
    }
  }

