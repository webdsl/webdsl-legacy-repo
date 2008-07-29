application com.example.basic

description {
	This is an automatically generated description
}

section pages

entity Message {
  sender   :: String
	message  :: Text
  date     :: DateTime
  reply_to -> Message
}

define page hello() {
  "Hello world!"
  "How are you doing\'s?"
}

define page viewMessage(m : Message) {
  form {
    input(m.sender) ":" input(m.message)
    action("Save", save())
  }
  action save() {
    m.save();
    return hello();
  }
}

