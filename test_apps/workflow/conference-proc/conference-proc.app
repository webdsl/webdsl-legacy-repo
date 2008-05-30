application org.webdsl.conference

description {
  Conference application
}

imports templates
imports datamodel/main
imports initialize
imports accesscontrol
imports workflows/conference


section main

  define page home() {
    main()
    define body() {
      section() {
        header(){"Conferences"}
        list {
          for(c : Conference in manager.conferencesList) {
            listitem{ output(c) }
          }
        }
      }
    }
  }
  
  
  // make associations from User to
  // - conferences
  // - authoredPapers
  // - reviewdedPapers
  // - bidPapers
  // allows more efficient creation of task list

  define conferenceProcedures(c : Conference) {
    conferenceProceduresList(c)
    
    if (c.pc != null && committeeHasProcedures(c.pc)) {
      committeeProceduresList(c.pc)
    }
    
    // display procedures on invitations
    for (i : CommitteeInvitation in c.pc.invitationsList) {
      if (committeeInvitationHasProcedures(i)) {
        "Invitation: "
        committeeInvitationProceduresList(i)
      }
    }
    
    // display procedures on bids
    if (Or[bidHasProcedures(b) | b : Bid in c.bidsList]) {
      "Bids: "
      for (b : Bid in c.bidsList) {
        if (bidHasProcedures(b)) {
          bidProceduresList(b) // first make new lines disappear. " on " text(b.paper.title)
        }
      }
    }
    
    // display procedures on papers and reviews
    for (p : Paper in c.papersList) {
      if (paperHasProcedures(p) 
          || Or[reviewHasProcedures(r) | r : Review in p.reviewsList]) {
        "Procedures exist for " output(p)
      }
      if (paperHasProcedures(p)) {
        paperProceduresList(p)
      }
    }
  }
  
  define paperProcedures(p : Paper) {
    paperProceduresList(p)
    for (r : Review in p.reviewsList) {
      if (reviewHasProcedures(r)) {
        reviewProceduresList(r)
      }
    }
  }
