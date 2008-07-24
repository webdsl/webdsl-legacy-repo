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

define page home() {
  "Hello world!"
}

