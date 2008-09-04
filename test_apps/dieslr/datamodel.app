module datamodel

section definition

  entity User {
    username         :: String (id, name)
    password         :: Secret
    sentMessages     -> Set<Message> (inverse=Message.sender)
    receivedMessages -> Set<Message> (inverse=Message.recipient)
    followers        -> Set<User> (inverse=User.following)
    following        -> Set<User>
    signupDate       :: DateTime
  }

  entity Message {
    sender    -> User
    recipient -> User
    replyTo   -> Message // Can be null
    text      :: Text
    date      :: DateTime
    original  :: Bool // true for the original message (so not copies for each recipient)
  }
