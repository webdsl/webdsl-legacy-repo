module committee

section data model 

  entity PcInvitation {
    user       -> User
    conference -> Conference
    reason     :: Text
    accepted   :: Bool
    name       :: String 
               := "Invitation for " + user.name 
                  + " to join PC of " + conference.name
  }
  
  extend entity Conference {
    pcInvitations -> Set<PcInvitation>
  }

procedures committee invitation

  procecure inviteProgramCommittee(c : Conference) {
    process {
      repeat { invitePcMember };
      finalizeProgramCommittee
    }  
  }
   
  procedure invitePcMember(c: Conference) {
    who { 
      principal in c.chairs 
    }
    view {
      var name : String
      var email : Email
      main()
      define contextSidebar() {
        conferenceProcedures(c)
      }
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
      pcInv.user := user;
      
      var pcInv : PcInvitation := PcInvitation{
        conference := c
      };
      pcInv.pcInvitationWorkflow.start(name, email);
      c.pcInvitations.add(pcInv);
    }
  }
  
  // why not 'show the committee' with a finalize action?
  
  procedure finalizeProgramCommittee(c: Conference) {
    description {
      If there are enough pc members that have accepted 
      the invitation, finalize the committee.
    }     
    who { 
      principal in c.chairs 
    }
    view {
      main()
      define contextSidebar() {
        conferenceProcedures(c)
      }
      define body() {
        section() {
          header{"Finalize the program committee"}
          showInvitations(c)
          showCommitte(c)
          form { action("Finalize", do()) }       
        }
      }
    }
  }
  
  define showCommitte(c : Conference) {
    section {
      header{"Program Committee members"}
      table {
        for (user : User in c.pcMembersList) {
          row {output(user)}
        }
      }
    }
  }

  define showInvitations(c : Conference) {
            // view all invitations with their statuses
          section() {
            header{"Invitations"}
            table {
              row {
                "" "Name" "Response"
              }
              for (pcInv : PcInvitation in c.pcInvitationsList) {
                row {
                  "Invitation: "
                  text(pcInv.user.name)
                  if (pcInv.pcInvitationWorkflow.performed) {
                    if (pcInv.accepted) {
                      "accepted"
                    } 
                    if (!pcInv.accepted) {
                      "rejected"
                    }
                  } 
                  if (!pcInv.pcInvitationWorkflow.performed) {
                    "not responded yet"
                  }
                }
              }
            }
          }
  }
  
operations pcInvitation

  procedure pcInvitation(pcInv : PcInvitation) {
    process {
      respondToInvitation
    }
  }

  operation respondToInvitation(pcInv : PcInvitation) {
    who { 
      principal = pcInv.user 
    }
    do {
      if (pcInv.accepted) {
        pcInv.conference.pcMembers.add(pcInv.user);
      }
    }
    view {
      title{"Please respond to the invitation"}
      derive operationPage from pcInv for (accepted, reason)
    }
  }