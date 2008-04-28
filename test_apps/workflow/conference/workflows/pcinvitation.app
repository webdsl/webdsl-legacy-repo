module workflows/pcinvitation

section data model 

  entity PcInvitation {
    user       -> User
    conference -> Conference
    reason     :: Text
    accepted   :: Bool
  }
  
  extend entity Conference {
    pcInvitations -> Set<PcInvitation>
  }

operations pcInvitation

  workflow pcInvitationWorkflow(pcInv : PcInvitation) {
    init(name : String, email : Email) {
      var user : User := getUser(name, email);
      pcInv.user := user;
    }
    done { pcInv.respond.performed }
  }
  
  /**
   * respond: for now, just editing the PcInvitation entity; later: a nice page
   */
  operation respond(pcInv : PcInvitation) {
    who { securityContext.principal = pcInv.user }
    when { !pcInv.respond.performed }
    do {
      pcInv.save();
      // add user to pcMembers if accepted
      if (pcInv.accepted) {
        pcInv.conference.pcMembers.add(pcInv.user);
      }
      pcInv.respond.performed := true;
    }
    view {
      title{"Please respond to the invitation"}
      derive operationPage from pcInv for (conference, accepted, reason)
    }
  }