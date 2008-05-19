application com.example.conference

description {
  Conference application
}

imports datamodel
imports templates
imports utils
imports initialize
imports accesscontrol
imports workflows/conference


section main

  define page home() {
    main()
    define body() {
      section() {
        header(){"Home page"}
      }
    }
  }
  
  define page createConference() {
    main()
    define body() {
      form {
        var conference : Conference := Conference{}
        header{"Create conference"}
        table {
          derive editRows from conference for (name, chairs)
          row { action("Create", create()) }
          action create() {
            conference.save();
            return conference(conference);
          }
        }
      }
    }
  }
  
  define conferenceOperations(c : Conference) {
    conferenceOperationsList(c)
    
    // display operations on invitations
    for (i : PcInvitation in c.pcInvitationsList) {
      if (pcInvitationHasOperations(i)) {
        "Invitation: "
        pcInvitationOperationsList(i)
      }
    }
    
    // display operations on bids
    if (Or[bidHasOperations(b) | b : Bid in c.bidsList]) {
      "Bids: "
      for (b : Bid in c.bidsList) {
        if (bidHasOperations(b)) {
          bidOperationsList(b) // first make new lines disappear. " on " text(b.paper.title)
        }
      }
    }
    
    // display operations on papers and reviews
    for (p : Paper in c.papersList) {
      if (paperHasOperations(p) || Or[reviewHasOperations(r) | r : Review in p.reviewsList]) {
        "Operations exist for " output(p)
      }
      if (paperHasOperations(p)) {
        paperOperationsList(p)
      }
    }
  }
  
  define paperOperations(p : Paper) {
    paperOperationsList(p)
    for (r : Review in p.reviewsList) {
      if (reviewHasOperations(r)) {
        reviewOperationsList(r)
      }
    }
  }