module workflows/committee
  
  note {
    In the current model committee chairs are appointed by the
    general chairs. However, chairs should be invited first and 
    then take over to invite committee members.
  }

section create committee

  procedure composeProgramCommittee(c : Conference) {
    who { securityContext.principal in c.chairs }
    view {
      var comm : Committee := Committee{
        name       := "Program Committee"
        conference := c
      };
      derive procedurePage from comm for (description, chairs)
    }
    do {
      c.pc := comm;
      for(u : User in comm.chairs) {
        comm.members.add(u);
      }
    }
    process {
      inviteCommittee(comm) // implies starting the workflow
    }
  }
 
section invitations

  entity CommitteeInvitation {
    user       -> User
    committee  -> Committee 
    reason     :: Text
    accepted   :: Bool
    name       :: String 
               := "Invitation for " + user.name 
                  + " to join " + committee.name
  }
  
  extend entity Committee {
    invitations -> Set<CommitteeInvitation>
  }
  
section invite members

  procedure inviteCommittee(comm : Committee) {
    process {
      repeat { 
        inviteCommitteeMember(c)
      };
      finalizeCommittee(c)
    }
  }

  procedure inviteCommitteeMember(comm : Committee) {
    who { securityContext.principal in comm.chairs }
    view {
      var name : String
      var email : Email
      main()
      define body() {
        form {
          table {
            row { "Name:" input(name) }
            row { "Email:" input(email) }
          }
          action("Invite", do())
        }
      }
    }
    do {
      var user : User := getUser(name, email);
      var inv : CommitteeInvitation := CommitteeInvitation{
        committee := comm
        user := user
      };
      comm.invitations.add(inv);
    }
    process {
      start respondToInvitation(inv)
    }
  }
  
  // why not 'show the committee' with a finalize action?
  
  // this should just be a button on the committee page
  // the committee should be an entity!
  
  procedure finalizeCommittee(c: Committee) {
    who { securityContext.principal in c.chairs }
  }

  define showInvitations(comm : Committee) {
    section() {
      header{"Invitations"}
      table {
        row {
          "" "Name" "Response"
        }
        for (inv : CommitteeInvitation in comm.invitationsList) {
          row {
            "Invitation: "
            output(inv.user)
            if (inv.respondToInvitation.performed) {
              if (inv.accepted) { "accepted" } 
              if (!inv.accepted) { "rejected" }
            }
            if (!inv.respondToInvitation.performed) {
              "not responded yet"
            }
          }
        }
      }
    }
  }

  procedure respondToInvitation(inv : CommitteeInvitation) {
    who { securityContext.principal = inv.user }
    view {
      main()
      title{"Invitation for " }
      define body() {
        output(inv.committee.description)
        form {
          derive editRows from inv for (accepted, reason)
          action("Submit", do())
        }
      }
    }
    do {
      if (inv.accepted) {
        inv.committee.members.add(inv.user);
      }
    }
  }
