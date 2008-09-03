module datamodel

section definition

  entity User {
    username         :: String (id, name)
    password         :: Secret
    sentMessages     -> Set<Message>
    receivedMessages -> Set<Message>
    followers        -> Set<User> (inverse=User.following)
    following        -> Set<User>
  }

  entity Message {
    user      -> User (inverse=User.sentMessages)
    recipient -> User (inverse=User.receivedMessages)
    replyTo   -> Message // Can be null
    text      :: Text
    date      :: DateTime
  }
