module pcinvite

section data model

entity PcInvite {
  user       -> User
  conference -> Conference
  reason     :: Text
  accepted   :: Bool
}

entity PcInviteTask : Task {
  invite -> PcInvite
  name   :: String := "Invitation to join PC of " + invite.conference.name
}

section pages

globals {
  function inviteAcceptTransition(t : PcInviteTask) : PcInviteTask {
    t.invite.conference.pc.add(securityContext.principal);
    t.invite.accepted := true;
    t.completed := true;
    return t;
  }
  function inviteRejectTransition(t : PcInviteTask) : PcInviteTask {
    t.invite.accepted := false;
    t.completed := true;
    return t;
  }
}

  
access control rules {
  rules template addPcMember(c : Conference) {
    securityContext.principal.isAdmin || securityContext.principal in c.chairs
  }
  rules template transitionToAcceptingPapers(c : Conference) {
    securityContext.principal.isAdmin || securityContext.principal in c.chairs
  }
}

define assemblePCView(c : Conference) {
  section {
    header{"Assembling PC"}
    "Current invites:"
    list {
      for(inv : PcInvite in c.pcInvitesList) {
        listitem {
          output(inv.user.name) "(Accepted yet: " output(inv.accepted) ")" 
          if(!inv.accepted && inv.reason != "") {
            "Reason: " output(inv.reason)
          }
        }
      }
    }
    addPcMember(c)
  }
  transitionToAcceptingPapers(c)
}

define addPcMember(c : Conference) {
  "Add member:"
  var pcInvite : PcInvite := PcInvite{ conference := c }
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
      c.pcInvites.add(pcInvite);
      var pcTask : PcInviteTask := PcInviteTask { assignee := pcInvite.user invite := pcInvite };
      pcTask.persist();
      //email(inviteMail(pcInvite));
      return conference(c);
    }
  }
}

define transitionToAcceptingPapers(c : Conference) {
  section {
    header{"Next step"}
    form {
      "Are you done assembling the program committee?"
      action("Accept papers", acceptPapers())
      
      action acceptPapers() {
        c.stage := acceptingPapers;
        return conference(c);
      }
    }
  }
}

access control rules {
  rules page pcInviteTask(t : PcInviteTask) {
    t in securityContext.principal.tasks
  }
}

define page pcInviteTask(t : PcInviteTask) {
  title {"Invite"}
  main()
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

