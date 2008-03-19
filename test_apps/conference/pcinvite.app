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

