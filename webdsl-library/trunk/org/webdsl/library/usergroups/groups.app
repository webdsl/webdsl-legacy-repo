module users/groups

section menu

  define groupMenu() 
  {
    menu{
      menuheader{ navigate(groups()){"Groups"} }
      for(g : UserGroup) {
        menuitem{ navigate(userGroup(g)){output(g.name)} }
      }
    }
  }
  
  define thisGroupMenu(g : UserGroup) { 
    menu{
      menuheader{ navigate(userGroup(g)){"This Group"} }
      menuitem{ joinGroup(g) }
      menuspacer{}
      menuitem{ navigate(editUserGroup(g)){"Edit this Group"} }
      menuitem{ navigate(membershipRequests(g)){"Membership Requests"} }
    }
  }
  
section groups

  define page groups() 
  {
    main()
    title{"Groups"}
    define body() {
      section{
        header{"Groups"}
        list { for(g : UserGroup) {
          listitem{ navigate(userGroup(g)){output(g.name)}  }
        } }
      }
    }
  }

  define page userGroup(g : UserGroup)
  {
    main()
    title{output(g.name) " Group"}
    define thisMenu() { thisGroupMenu(g) }
    define body() {
      section{
        header{output(g.name) " Group"}
        par{output(g.description)}
        section{
          header{"Moderators"}
          output(g.moderators)
        }
        section{
          header{"Members"}
          output(g.members)
        }
      }
    }
  }

  define joinGroup(g : UserGroup)
  {
    form{
      actionLink("Join this Group", joinGroup())
      action joinGroup() {
        g.requested.add(securityContext.principal);
      }
    }
  }
  
  define page membershipRequests(g : UserGroup)
  {
    main()
    define groupOperationsMenu(){ modGroupOperationsMenu(g)}
    define body() {
      section{
        header{"Membership Requests for " output(g) " Group"}
        list { for(u : User in g.requestedList) {
          listitem { form {
            output(u)
            action("Approve Membership", approve(u))
            action("Reject Membership", reject(u))
          } }
        } }
      }
      action approve(u : User) {
        g.members.add(u);
        g.requested.remove(u);
        g.persist();
        //fix for bug with inverse relations
        u.groups.add(g);
        // note: send email confirmation
        return membershipRequests(g);
      }
      action reject(u : User) {
        g.requested.remove(u);
        g.persist();
        // note: send email confirmation
        return membershipRequests(g);
      }
    }
  }
  
  define page editUserGroup (userGroup : UserGroup) {
    main()    
    define thisMenu() { thisGroupMenu(userGroup) }
    define body() {
      section{
        header{
          "Edit "
          "UserGroup"
          " "
          output(userGroup.name)
        }
        form{
          table{
            editRowsUserGroup(userGroup)
          }
          action("Save", save())     
          action("Cancel", cancel())
        }
      }
      action cancel(){
        return userGroup(userGroup);
      }
      action save(){
        userGroup.save();
        return userGroup(userGroup);
      }
    }
  }