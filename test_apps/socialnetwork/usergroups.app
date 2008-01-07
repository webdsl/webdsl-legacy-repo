module usergroups

section groups.

  define groupNewUserGroup(u:User)
  {
    var newGroup : UserGroup := UserGroup{};
    table
    {
      form
      {
        row{input(newGroup.name)}
        row{action("create group",create())}
        action create()
        {
          newGroup.owner := u;
          newGroup.members.add(u);
          u.groups.add(newGroup);
          var ptemp: GroupPage:=GroupPage{};
          ptemp.name := newGroup.name + "page";
          ptemp.group :=newGroup;
          ptemp.content := "group page";
          newGroup.page := ptemp;
          newGroup.viewAccess := priv;
          newGroup.save();       
        }
      }
    } 
  }
  
  define page viewUserGroup(g: UserGroup)
  {
    main()
    define body()
    {
      header{"Owner"}
      navigate(viewUser(g.owner)) { output(g.owner.name) } 
      
      header{"Moderators"}
      list { 
        for(u : User where u in g.moderators) 
        { 
          listitem{ navigate(viewUser(u)) { output(u.name) } } 
        } 
      }
      
      header{"Group Members"}
      list { 
        for(u : User where u in g.members) 
        { 
          listitem{ navigate(viewUser(u)) { output(u.name) } } 
        } 
      }
      
      header{"Group Page"}
      output(g.page.name)   
      output(g.page.content)   
      
  
      section{navigate(editUserGroup(g)) { "Edit my group" }}
      section{navigate(editGroupPage(g.page)) { "Edit group page" }}
      
    }
  }
  
  
  define groupIncomingMembershipRequests(ug:UserGroup)
  {
    table
    {
      form
      {
        for(f : MembershipRequest where f.requestee = ug) 
        { 
          row{ output(f.requester.username)  action("accept",acceptMemRequest(f))} 
        } 
      
        action acceptMemRequest(f: MembershipRequest)
        {
          f.requester.groups.add(f.requestee);
          f.requestee.members.add(f.requester);
          
          f.requester.membershipRequests.remove(f);
          f.requestee.incomingMembershipRequests.remove(f);
          f.delete();   
        }
      }
    }
  } 
  define page editUserGroup(ug : UserGroup)
  {
    main()
    
    define body()
    {
      header{"Group info"}
      table
      {
        form
        {
          row
          {
            "Group name"
            input(ug.name)  
          }     
          row
          {
            "Group page access"
            input(ug.viewAccess)
          }
          row
          {
            action("save",saveUserGroup())
          }
          action saveUserGroup()
          {
            ug.save();
          }
        } 
      }
     
      header{"Current Moderators"}
      table
      {
        form
        {
          for(m : User where m in ug.moderators) 
          { 
            row{ output(m.username) action("demote",demoteMod(m)) } 
          } 
          
          action demoteMod(mod:User)
          {
            ug.moderators.remove(mod);
          }
        }
      }
      
      header{"New Moderators"}
      table
      {
        form
        {
          for(m : User where m in ug.members && !(m in ug.moderators )&& m!=ug.owner) 
          { 
            row{ output(m.username) action("promote",promoteMod(m)) } 
          } 
          action promoteMod(mod:User)
          {
            ug.moderators.add(mod);  
          }
        }
      }
      
      header{"Incoming Group membership requests"}
    
      groupIncomingMembershipRequests(ug)
      
    }
  }  
  
  define page editGroupPage(p: GroupPage)
  {
    main()
    define body()
    {
      groupPageEdit(p)
    }
  }
  
  define groupPageEdit(p:GroupPage)
  {
    form
    {
      input(p.content)
      
      action("save",savePage())
      action savePage()
      {
        p.save();
      }
    }
  }