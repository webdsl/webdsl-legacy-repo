module pcinvite

section data model

  entity PcInvite {
    user       -> User
    conference -> Conference
    reason     :: Text
    accepted   :: Bool
  }

  entity PcInvitedTask : Task {
    invite -> PcInvite
    name   :: String := "Invitation to join PC of " + invite.conference.name
  }

  entity AssemblePcTask : Task {
    conference -> Conference
    name       :: String := "Assemble a PC for " + conference.name
  }

section business logic

  globals {
    function initPcInviteStage(c : Conference) : Conference {
      var assemblePcTask : AssemblePcTask := AssemblePcTask { conference := c };
      assemblePcTask.persist();
      for(u : User in c.chairsList) {
        assignTask(assemblePcTask, u);
      }
      return c;
    }

    function inviteAcceptTransition(t : PcInvitedTask) : PcInvitedTask {
      t.invite.conference.pc.add(securityContext.principal);
      t.invite.accepted := true;
      t.completed := true;
      return t;
    }

    function inviteRejectTransition(t : PcInvitedTask) : PcInvitedTask {
      t.invite.accepted := false;
      t.completed := true;
      return t;
    }
  }

section pages
  
  access control rules {
    rules page assemblePcTask(t : AssemblePcTask) {
      t in securityContext.principal.tasks
    }
  }

  define page assemblePcTask(t : AssemblePcTask) {
    title{"Assemble PC for " output(t.conference.name)}
    main()
    define contextSidebar() {
      conferenceSidebar(t.conference)
    }
    define body() {
      section {
        header{"Assembling PC"}
        "Current invites:"
        list {
          for(inv : PcInvite in t.conference.pcInvitesList) {
            listitem {
              output(inv.user.name) " (Accepted yet: " output(inv.accepted) ")" 
              if(!inv.accepted && inv.reason != "") {
                "Reason: " output(inv.reason)
              }
            }
          }
        }
        "Add member:"
        var pcInvite : PcInvite := PcInvite{ conference := t.conference }
        var name : String
        var email : Email
        form {
          table {
            row { "Name:" input(name) }
            row { "Email:" input(email) }
          }
          action("Invite", invite())
          action invite() {
            pcInvite.user := getUser(name, email);
            pcInvite.persist();
            t.conference.pcInvites.add(pcInvite);
            var pcTask : PcInvitedTask := PcInvitedTask { invite := pcInvite };
            pcTask.persist();
            assignTask(pcTask, pcInvite.user);
            return conference(t.conference);
          }
        }
      }
      section {
        header{"Complete"}
        form {
          "Are you done assembling the program committee?"
          action("Accept papers", acceptPapers())
          
          action acceptPapers() {
            initAcceptingPapers(t.conference);
            t.completed := true;
            return conference(t.conference);
          }
        }
      }
    }
  }

  access control rules {
    rules page pcInvitedTask(t : PcInvitedTask) {
      t in securityContext.principal.tasks
    }
  }

  define page pcInvitedTask(t : PcInvitedTask) {
    title {"Invite"}
    main()
    define contextSidebar() {
      conferenceSidebar(t.invite.conference)
    }
    define body() {
      header{"Confirm PC Invite for conference " output(t.invite.conference) }
      form {
        "Optional reason:" input(t.invite.reason)
        action("Accept", accept())
        action("Reject", reject())
        
        action accept() {
          inviteAcceptTransition(t);
          return message("You accepted your invite successfully, thanks!");
        }
        
        action reject() {
          inviteRejectTransition(t);
          return message("You rejected your invite, thanks anyway!");
        }
      }
    }
  }

