application org.webdsl.socialnetwork

description {
   This application is a simple social network.
}

imports authentication
imports accesscontrol
imports templates
imports init
imports datamodel
imports usergroups

section home.

  define homePageEdit(p:Page)
  {
    form
    {
      input(p.name)
      input(p.content)
      action("save",savePage())
      action savePage()
      {
        p.save();
      }
    }
  }
  define userEdit(u:User)
  {
    form
    {
      block("name")
      {
        "Username"
        input(u.username) 
        action("save username", saveUser())
      }
      block("access")
      {
        "User page access"
        input(u.viewAccess)
      }
      action saveUser() {
        u.save();
      }
    }
  }

  define page editPage(p: Page)
  {
    main()
    define body()
    {
      homePageEdit(p)
    }
  }
  
  define page viewUser(u : User)
  {
    var p : Page := u.page;
    
    main()

    define body() {
      title{output(p.name)}
      section {
        header{output(p.name)}
        section { "Member: " output(u.username) }
        section { "My Friends: " output (u.friends)}
        section { "My Groups: " output (u.groups)}
        section { output(p.content) }
        div("owner")
        {
          section{navigate(editPage(p)) { "Edit my page" }}
        }
      }
    }
  }
  
  define page editUser(u : User)
  {
    main()
    define body(){
      header{"Personal Information"}
      userEdit(u)
      header{"Your Page"}
      homePageEdit(u.page)
    }
  } 
  
  define groupRequests(u:User)
  {
    form
    {  
      list { 
        for(ug : UserGroup where u.potentialGroup(ug)) 
        { 
          listitem{output(ug.name) action("request group membership",reqGroup(ug)) } 
        } 
      }
      action reqGroup(ug: UserGroup) {
        var mreq : MembershipRequest := MembershipRequest
        {
          requester := u
          requestee := ug
        };
        mreq.save();
        u.membershipRequests.add(mreq);
        ug.incomingMembershipRequests.add(mreq);
      }
    }
  }
  define groupStartedMembershipRequests(u:User)
  {
    form
    {
      list {
        for(memreqs : MembershipRequest in u.membershipRequestsList) 
        { 
          listitem{ output(memreqs.requestee.name) action("cancel",cancelMemRequest(memreqs)) } 
        } 
      }
      action cancelMemRequest(f:MembershipRequest)
      {
        f.delete();
        f.requestee.incomingMembershipRequests.remove(f);
        f.requester.membershipRequests.remove(f);
      }
    }
  }
  define page groups(u:User)
  {
    main()
    define body()
    {
      header{"My Groups"}
      list {
        for(myg :UserGroup in u.groupsList) 
        { 
          listitem{navigate(viewUserGroup(myg)) { output(myg.name) }} 
        } 
      }
      header{"New Groups and Memberships"}
      groupRequests(u)
      groupNewUserGroup(u)
      header{"Your Group membership requests"}
      groupStartedMembershipRequests(u)
    }
  }

  define friendNewRequests(u:User)
  {
    form
    {  
      list { 
        for(us : User where u.potentialFriend(us) )
        { 
          listitem{output(us.username) action("request friendship",reqFriend(us)) } 
        } 
      }
      action reqFriend(us: User) {
        var freq : FriendRequest := FriendRequest
        {
          requester := u
          requestee := us
        };
        freq.save();
        
        u.friendRequests.add(freq);
        us.incomingFriendRequests.add(freq);
      }
    }
  }
  define friendStartedRequests(u:User)
  {
    form
    {
      list {
        for(ownfreqs : FriendRequest in u.friendRequestsList) 
        { 
          listitem{ output(ownfreqs.requestee.username) action("cancel",cancelFriendRequest(ownfreqs)) } 
        } 
      }
      action cancelFriendRequest(f:FriendRequest)
      { 
        f.requester.friendRequests.remove(f);
        f.requestee.incomingFriendRequests.remove(f);   
        f.delete();
      }
    }
  }  
  define friendIncomingRequests(u:User)
  {
    form
    {
      list {
        for(incfreqs : FriendRequest in u.incomingFriendRequestsList) 
        { 
          listitem{ output(incfreqs.requester.username)  action("accept",acceptFriendRequest(incfreqs))} 
        } 
      }
      action acceptFriendRequest(f:FriendRequest)
      {
        f.requester.friends.add(f.requestee);
        f.requestee.friends.add(f.requester);
        
        f.requester.friendRequests.remove(f);
        f.requestee.incomingFriendRequests.remove(f);
        f.delete();
  
      }
    }
  } 
  
  define page friends(u:User)
  {
    main()
    define body()
    {
      
      header{"My Friends"}
      list {
        for(myf : User in u.friendsList) 
        { 
          listitem{ navigate(viewUser(myf)){output(myf.username)}} 
        } 
      }
      header{"New Friendship requests"}
      friendNewRequests(u)
      header{"Your Friendship requests"}
      friendStartedRequests(u)
      header{"Incoming Friendship requests"}
      friendIncomingRequests(u)
    }
  }

  define page home() {
    main()

    define body() {
      title{"Social Network Start"}
      section{
        section {
          header{"All members"}
          list { 
            for(u : User) 
            { 
              listitem{ navigate(viewUser(u)) { output(u.name) } } 
            } 
          }
          header{"All groups"}
          list {
            for(g : UserGroup) 
            { 
              listitem{ navigate(viewUserGroup(g)) { output(g.name) } } 
            } 
          }
        }
      }
    }
  }
  
        