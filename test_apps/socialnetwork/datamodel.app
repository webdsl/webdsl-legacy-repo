module datamodel

section definition


  entity User {
    username :: String(name)
    password :: Secret
    page     -> Page
    friends  -> Set<User>
    groups   -> Set<UserGroup>
    friendRequests -> Set<FriendRequest>
    incomingFriendRequests -> Set<FriendRequest>
    membershipRequests -> Set<MembershipRequest>
    
    function potentialFriend (f:User): Bool
    {
      if (this != f
      && !(f in [fr.requester for (fr : FriendRequest in this.incomingFriendRequests)])
      && !(f in [fr.requestee for (fr : FriendRequest in this.friendRequests)])
      && !(f in this.friends)
      )
      {
        return true;
      }
      else
      {
        return false;
      }
      
    } 
            
    function potentialGroup (ug:UserGroup): Bool
    {
      if (!(ug in [mr.requestee for (mr : MembershipRequest in this.membershipRequests)])
       && !(ug in this.groups)
      )
      {
        return true;
      }
      else
      {
        return false;
      }
      
    }    
  }
  
  entity UserGroup {
    name       :: String (name)
    owner      -> User
    moderators -> Set<User>
    members    -> Set<User> (inverse=User.groups)
    page       -> GroupPage
    incomingMembershipRequests -> Set<MembershipRequest>
  }

  entity Page {
    name     :: String (name)
    owner    -> User (inverse = User.page)
    content  :: Text
  }
  entity GroupPage {
    name     :: String (name)
    content  :: Text
    group    -> UserGroup (inverse = UserGroup.page)
  }
  
  entity FriendRequest{
    requester -> User (inverse=User.friendRequests)
    requestee -> User (inverse=User.incomingFriendRequests)
  }
  
  entity MembershipRequest{
    requester -> User (inverse=User.membershipRequests)
    requestee -> UserGroup (inverse=UserGroup.incomingMembershipRequests)
  }
  
  
  section anti-view-edit-page-HACKS
  
  define page viewFriendRequest(f:FriendRequest){}
  define page editFriendRequest(f:FriendRequest){}
  define page createFriendRequest(){}
  define page allFriendRequest(){}
  define page viewMembershipRequest(m:MembershipRequest){}
  define page editMembershipRequest(m:MembershipRequest){}
  define page createMembershipRequest(){}
  define page allMembershipRequest(){}   