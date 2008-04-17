application presentationexampleac

description {
   This application is a simple social network.
}

imports authentication
imports accesscontrol
imports templates
imports init
imports datamodel

section pages

  define page editMemberPage(m:MemberPage){
    main()
    define body(){
      section{
        header{ "Edit Member Page Of " output(m.owner.username) }
        form{
          table{
            row{ "Text:" input(m.content) }
            row{ "ViewAccess:" input(m.viewAccess)}
          }
          action("Save", save())
      
        }
        action save(){
          m.save();
          return memberPage(m);
        }
      }
    }
  }
  
  define page memberPage(m:MemberPage){
    main()
    define body(){
      section{
        header{ "Member Page of " output(m.owner.username) }
        table{ 
          row{ "Text: " output(m.content) }
          row{ navigate(editMemberPage(m)){
                 "Edit this page"
               }
          }
        }  
      }
    }
  }
  
  define page editUser(u:User){
    main()
    define body(){
      section{
        header{ "Edit User Details of " output(u.username) }
        form{
          table{
            row{ "Username:" input(u.username) }   
            //row{ "Friends: " input(u.friends) }
          
          row{action("Save", save())}
          }
        }

        action save(){
          u.save();
          return user(u);
        }
      }
   
      
      friends(u)
    }
  }
  
  define page user(u:User){
    main()
    define body(){
      section{
        header{ "User " output(u.username) }
        table{ 
          row{ "Username: " output(u.username) }
          row{ "Friends: " output(u.friends) }
          row{ navigate(memberPage(u.page)){
                 "Go To Member Page"
               }
          }
        }  
      }
    }
  }

  define page home() {
    main()

    define body() {
      login()
      

      
    }
  }
  
  
  
  define friendNewRequests(u:User)
  {
    table
    {
      form
      {  
        
        for(us : User where !(u = us || us in u.friends))
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
          
          //u.friendRequests.add(freq);
         // us.incomingFriendRequests.add(freq);
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
  
  define friends(u:User)
  {
    section{

      header{"My Friends"}
	table{
      for(myf : User in u.friendsList) 
      { 
       row{ section{output(myf.username)}}      
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
  
        