module committee
  
section committee

  entity Committee {
    name        :: String (name) // e.g. "Program Committee", "Steering Committee"
    chairs      -> Set<User>
    members     -> Set<User>
    conference  -> Conference // do we need this?
    // instead of a link to the conference we could 
    // use a description of the purpose; i.e. can we
    // make the Committee workflow reusable (what is the
    // difference with a group?)
    description :: Text
  }

  define showCommittee(c : Committee) {
    section {
      header{"Committee Members"}
      table {
        for (user : User in c.membersList) {
          row {
            output(user)
            // affilliation
            if(user in c.chairs) { "(chair)" }
          }
        }
      }
    }
  }
  
section invitation

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

procedures committee invitation

  procedure composeProgramCommittee(c : Conference) {
    who {
      principal in c.chairs
    }
    view {
      var comm : Committee := Committee{
        conference := c
      };
      derive editPage from comm for (name, chairs)
      // note chairs should be invited first and then take over
      // to invite committee members
    }
    do {
      c.pc := comm;
    }
    process {
      inviteCommittee(c) // implies starting the workflow
    }
  }

  procecure inviteCommittee(c : Committee) {
    process {
      repeat { inviteCommitteeMember(c) };
      finalizeCommittee(c)
      // approveCommittee(c) // steering committee and others should approve
    }
  }
   
  procedure inviteCommitteeMember(c: Committee) {
    who { principal in c.chairs }
    view {
      var name : String
      var email : Email
      form {
        table {
          row { "Name:" input(name) }
          row { "Email:" input(email) }
        }
        action("Invite", do())
      }
    }
    do {
      var user : User := getUser(name, email);
      var inv : CommitteeInvitation := CommitteeInvitation{
        committee := c
        user := user
      };
      c.invitations.add(inv);
    }
    process {    
      respondToInvitation(inv)
    }
  }
  
  // why not 'show the committee' with a finalize action?
  
  // this should just be a button on the committee page
  // the committee should be an entity!
  
  procedure finalizeCommittee(c: Committee) {
    who { principal in c.chairs }
  }
  

  define showInvitations(c : Committee) {
    section() {
      header{"Invitations"}
      table {
        row {
          "" "Name" "Response"
        }
        for (inv : CommitteeInvitation in c.invitationsList) {
          row {
            "Invitation: "
            output(inv.user)
            // this s
            if (inv.invitation.performed) {
              if (inv.accepted) { "accepted" } 
              if (!inv.accepted) { "rejected" }
            }
            if (!inv.invitationWorkflow.performed) {
              "not responded yet"
            }
          }
        }
      }
    }
  }

operations invitation

  procedure respondToInvitation(inv : CommitteeInvitation) {
    who { principal = inv.user }
    view {
      title{"Invitation for " }
      output(inv.description)
      derive operationPage from inv for (accepted, reason)
    }
    do {
      if (inv.accepted) {
        inv.committee.members.add(inv.user);
      }
    }
  }