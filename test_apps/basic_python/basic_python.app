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

section pages

define page home() {
  "Hello world!"
}

define page viewMessage(m : Message) {
  form {
    input(m.sender) ":" input(m.message)
    action("Save", save())
  }
  action save() {
    m.save();
    return home();
  }
}

