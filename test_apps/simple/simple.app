application com.example.simple

description {
	This is an automatically generated description
}

section data

  entity Person {
    name     :: String
    age      :: Int
    messages -> Set<Message> (inverse=Message.sender)
    groups   -> Set<Group>
  }

  entity Group {
    name     :: String
    members  -> Set<Person> (inverse=Person.groups)
  }

  entity Message {
    sender -> Person
    text   :: Text
  }


section pages

define main() {
  header { "Zef's awesome page" }
  spacer
  body()
  spacer
  "(C) Zef Hemel"
}

define body() {
}

function sayHello(p : Person) : String {
  return "Hello " + p.name;
}

var zef : Person := Person { name := "Zef", age := 26 };

define page root() {
  var p : Person := Person{};
  main()
  define body() {
    header { "Messages" }
    list {
      for(m : Message) {
        listitem { 
          output(m.sender.name) ": " output(m.text)
        }
      }
    }
    form {
      header { "Add message" }
      var m : Message := Message{};
      var sender : Person
      "From: " input(sender)
      "Message: " input(m.text)
      action("Add", addMessage())

      action addMessage() {
        sender.messages.add(m);
        m.save();
        return root();
      }
    }
    header { "People" }
    list {
      for(p : Person) {
        listitem {
          output(p)
          list {
            for(m : Message in p.messages) {
              listitem { output(m.text) }
            }
          }
        }
      }
    }
    form {
      header { "Register user" }
      "Name: " input(p.name)
      "Age: " input(p.age)

      action("Add", add())
      action add() {
        p.save();
        return root();
      }
    }
    header { "Groups" }
    list {
      for(g : Group) {
        listitem {
          output(g.name)
          list {
            for(p : Person in g.members) {
              listitem { 
                form {
                  output(p.name) 
                  action("Remove from group", remove(p, g))

                  action remove(p : Person, g : Group) {
                    //g.members.remove(p);
                    p.groups.remove(g);
                    return root();
                  }
                }
              }
            }
          }
        }
      }
    }
    var newGroup : Group := Group{};
    form {
      header { "Add group" }
      "Name: " input(newGroup.name)

      action("Create", createGroup())
      action createGroup() {
        newGroup.save();
        return root();
      }
    }
  }
}

define page person(p : Person) {
  output(sayHello(p))
  var g : Group := Group{};
  form {
    "Add to group: " input(g)
    action("Add", add())

    action add() {
      g.members.add(p);
      return root();
    }
  }
}
