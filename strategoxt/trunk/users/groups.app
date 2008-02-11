module users/groups

section menu

  define groupMenu() 
  {
    menu{
      menuheader{ navigate(groups()){"Groups"} }
      groupOperationsMenu()
      for(g : UserGroup) {
        menuitem{ output(g) }
      }
    }
  }

  define groupOperationsMenu() { }
  
section groups

  define page groups() 
  {
    main()
    title{"Groups"}
    define body() {
      section{
        header{"Groups"}
        list { for(g : UserGroup) {
          listitem{ output(g) }
        } }
      }
    }
  }

  define page userGroup(g : UserGroup)
  {
    main()
    title{output(g.name) " Group"}
    define groupOperationsMenu() { 
      menuitem{ joinGroup(g) }
      menuitem{ navigate(editUserGroup(g)){"Edit this Group"} }
      menuitem{ navigate(membershipRequests(g)){"Membership Requests"} }
      menuspacer{}
    }
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