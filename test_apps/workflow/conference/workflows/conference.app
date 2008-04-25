module workflows/conference

operations conference  
  // old; replaced by performed etc.
  enum ConferenceStage {
    assemblePC("Assembling the PC"),
    acceptingPapers("Accepting papers"),
    bidOnPapers("Bidding on papers"),
    decideReviewAssignment("Decide on who will review which papers"),
    reviewing("Reviewing"),
    decideOnAcceptance("Decide on acceptance of papers"),
    submitFinalPapers("Results known, submit final papers"),
    conferenceCompleted("Ready for the actual conference")
  }

  workflow conferenceWorkflow(c : Conference) {
    init {
      c := Conference{}
    }
    done { c.finalizeConference.performed }
  }
  
  /**
   * Inviting pc members. Separate workflows manage this invitation. 
   */
  operation invitePcMember(c: Conference) {
    who { securityContext.principal in c.chairs }
    when { !c.finalizePc.performed }
    do {
      pcInv : PcInvitation := newPcInvitationWorkflow(name, email);
      c.pcInvitations.add(pcInv);
    }
    view {
      var name : String
      var email : Email
      main()
      define contextSidebar() {
        conferenceOperations(c)
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
  }
  
  /**
   * If there are enough pc members that have accepted the invitation, we can finalize the pc
   */
  operation finalizePc(c: Conference) {
    who { securityContext.principal in c.chairs }
    when { status.stage = assemblePC }
    do {
      c.finalizePc.performed := true;
    }
    view {
      main()
      define contextSidebar() {
        conferenceOperations(c)
      }
      define body() {
        section() {
          header{"Finalize the program committee"}
          
          // view all invitations with their statuses
          section() {
            header{"Invitations"}
            table {
              row {
                "" "name" "response"
              }
              for (pcInv : PcInvitation in c.pcInvitations) {
                row {
                  "Invitation: "
                  pcInv.user.name
                  if (pcInv.done) {
                    if (pcInv.accepted) {
                      "accepted"
                    } else {
                      "rejected"
                    }
                  } else {
                    "not responded yet"
                  }
                }
              }
            }
          }
          
          // view the list of program committee members
          section() {
            header{"Program Committee members"}
            table {
              for (user : User in c.pcInvitations) {
                row {
                  output(user)
                }
              }
            }
          }
        
          form {
            action("Finalize", do())
          }       
        }
      }
    }
  }
  
  // hier verder
  operation submitPaper(c: Conference) {
    when { status.stage = acceptingPapers }
    view {
      title{"Submit paper"}
      main()
      define contextSidebar() {
        conferenceOperations(c)
      }
      define body () {
        section(){
          var paper : Paper := Paper{}
          header(){"Submit paper"}
          form(){
            table(){
              editRowsPaper(paper){}
            }
            action("Save", save()){}
            action("Cancel", cancel()){}
          }
        }
        action cancel ( )
        {
          return home();
        }
        action save ( )
        {
          paper.save();
          return paper(paper);
        }
      }
    }
  }
  
  operation allPapersReceived(c : Conference) {
    when { c.finalizePc.performed && !c.allPapersReceived.performed }
  }
  
  operation decideReviewAssignment(c : Conference) {
    when { c.allPapersReceived.performed && !c.decideReviewAssignment.performed }
  }
  
  operation decideOnAcceptance(c : Conference) {
    when { c.decideReviewAssignment.performed && !c.decideOnAcceptance.performed }
  }
  
  operation finalizeConference(c : Conference) {
    when { c.decideOnAcceptance.performed && !finalizeConference.performed }
  }

/*
section conference pages

  define page conference(c : Conference) {
    main()
    title{"Conference: " output(c.name) }

    define contextSidebar() {
      conferenceSidebar(c)
    }

    define body() {
      header{output(c.name)}
      section {
        par { "Conference stage: " output(c.stage.name) }
//        conferenceTasks(c)
        header{"Program Committee"}
        list {
          for(u : User in c.pcMembersList) {
            listitem { output(u) }
          }
        }
        if(c.pcMembers.length = 0) {
          "No PC members yet."
        }
        submittedPapers(c)
        par { navigate(submitPaper(c)) { "Submit paper" } }
      }
    }
  }
  
  define submittedPapers(c : Conference) {
    section {
      header {"Conference papers"}
      list {
        for(p : Paper in c.papersList where (c.stage = conferenceCompleted && p.final) || (c.stage != conferenceCompleted)) {
          listitem { output(p) }
        }
      }
    }
  }*/