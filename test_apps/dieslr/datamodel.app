module datamodel

section definition

  entity User {
    username :: String (id, name)
    password :: Secret
    updates -> Set<Update>
  }

  entity Update {
    user    -> User (inverse=User.updates)
    replyTo -> Update // Can be null
    text    :: Text
    date    :: DateTime
  }

