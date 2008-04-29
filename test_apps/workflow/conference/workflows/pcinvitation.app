module workflows/pcinvitation

section data model 

  entity PcInvitation {
    user       -> User
    conference -> Conference
    reason     :: Text
    accepted   :: Bool
    name :: String := user.name
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
    done { pcInv.respondToInvitation.performed }
  }
  
  /**
   * respond: for now, just editing the PcInvitation entity; later: a nice page
   */
  operation respondToInvitation(pcInv : PcInvitation) {
    who { securityContext.principal = pcInv.user }
    when { !pcInv.conference.conferenceWorkflow.done && pcInv.pcInvitationWorkflow.started && !pcInv.respondToInvitation.performed }
    do {
      pcInv.save();
      // add user to pcMembers if accepted
      if (pcInv.accepted) {
        pcInv.conference.pcMembers.add(pcInv.user);
      }
      pcInv.respondToInvitation.performed := true;
    }
    view {
      title{"Please respond to the invitation"}
      derive operationPage from pcInv for (conference, accepted, reason)
    }
  }