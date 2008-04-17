module datamodel

section data definitions

  entity User {
    username :: String(name)
    password :: Secret
    page     -> MemberPage
    friends  -> Set<User>
  }

  entity MemberPage {
    owner    -> User (inverse = User.page)
    content  :: Text
  }
  
  extend entity User {
    friendRequests -> Set<FriendRequest>
    incomingFriendRequests -> Set<FriendRequest>
  } 
      
  entity FriendRequest{
    requester -> User (inverse=User.friendRequests)
    requestee -> User (inverse=User.incomingFriendRequests)
  }