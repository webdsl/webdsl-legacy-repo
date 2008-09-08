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

  define page user(u:User){ derive viewPage from u }

  define page userGroup(u:UserGroup){ derive viewPage from u }
  
  define page friendRequest(u:FriendRequest){ derive viewPage from u }

  define page membershipRequest(u:MembershipRequest){ derive viewPage from u }
  
  define page viewMode(u:ViewMode){ derive viewPage from u }
  
  define page page(u:Page){ derive viewPage from u }
  
  define page groupPage(u:GroupPage){ derive viewPage from u }

  define homePageEdit(p:Page)
  {
    table
    {
      form
      {
        row{input(p.name)}
        row{input(p.content)}
        row{action("save",savePage())}
        action savePage()
        {
          p.save();
        }
      }
    }
  }
  define userEdit(u:User)
  {
    table
    {
      form
      {
        row
        {
          "Username: "
          input(u.username)
        }
        row
        {
          "User page access: "
          input(u.viewAccess)
        }
        row
        {
          action("save user", saveUser())
        }
        action saveUser() {
          u.save();
        }
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
        section{navigate(editPage(p)) { "Edit my page" }}   
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
    table
    {
      form
      {  
     
        for(ug : UserGroup where u.potentialGroup(ug)) 
        { 
          row{output(ug.name) action("request group membership",reqGroup(ug)) } 
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
  }
  define groupStartedMembershipRequests(u:User)
  {
    table
    {
      form
      {
 
        for(memreqs : MembershipRequest in u.membershipRequestsList) 
        { 
          row{ output(memreqs.requestee.name) 
          row{action("cancel",cancelMemRequest(memreqs)) }} 
        } 
       
        action cancelMemRequest(f:MembershipRequest)
        {
          f.delete();
          f.requestee.incomingMembershipRequests.remove(f);
          f.requester.membershipRequests.remove(f);
        }
      }
    }
  }
  define page groups(u:User)
  {
    main()
    define body()
    {
      header{"My Groups"}
      table {
        for(myg :UserGroup in u.groupsList) 
        { 
          row{navigate(viewUserGroup(myg)) { output(myg.name) }} 
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
    table
    {
      form
      {  
        
        for(us : User where u.potentialFriend(us) )
        { 
          row{output(us.username) action("request friendship",reqFriend(us)) }
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
    
  }
  
  define friendStartedRequests(u:User)
  {
    table
    {
      form
      {
        
        for(ownfreqs : FriendRequest in u.friendRequestsList) 
        { 
          row {output(ownfreqs.requestee.username) action("cancel",cancelFriendRequest(ownfreqs))}
        } 
        
        action cancelFriendRequest(f:FriendRequest)
        { 
          f.requester.friendRequests.remove(f);
          f.requestee.incomingFriendRequests.remove(f);   
          f.delete();
        }
      }
    }
  }  
  define friendIncomingRequests(u:User)
  {
    table
    {
      form
      {
        for(incfreqs : FriendRequest in u.incomingFriendRequestsList) 
        { 
          row
          {
            output(incfreqs.requester.username)
            action("accept",acceptFriendRequest(incfreqs))
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
    
  } 
  
  define page friends(u:User)
  {
    main()
    define body()
    {

      header{"My Friends"}

      for(myf : User in u.friendsList) 
      { 
        section{navigate(viewUser(myf)){output(myf.username)}}      
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
    
      table{ 
        row{login()}
      }
  
      header{"All members"}
      
      table
      {
        for(u : User where viewAllowed(u)) 
        { 
          row{ navigate(viewUser(u)) { output(u.name) } } 
        }
      }
      
      header{"All groups"}
      table
      { 
        for(g : UserGroup where groupViewAllowed(g)) 
        { 
          row{navigate(viewUserGroup(g)) { output(g.name) }} 
        }  
      }
      
    }
  }
  
        