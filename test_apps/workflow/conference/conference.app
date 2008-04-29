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
    for (b : Bid in c.bidsList) {
      if (bidHasOperations(b)) {
        "Bids: "
        bidOperationsList(b)
      }
    }

    // display operations on papers and reviews
    for (p : Paper in c.papersList) {
      if (paperHasOperations(p) || true in [reviewHasOperations(r) | r : Review in p.reviewsList]) {
        "Operations exist for " output(p)
      }
      if (paperHasOperations(p)) {
        paperOperationsList(p)
      }
    }
    
    
  }
  
  define displayPaperOperations(p : Paper) {
    paperOperationsList(p)
    for (r : Review in p.reviewsList) {
      if (reviewHasOperations(r)) {
        reviewOperationsList(r)
      }
    }
  }
    
  /*
    define page conference(c : Conference) {
      main()
      define contextSidebar() {
        conferenceOperations(c)
      }
      define body() {
        header{"Conference " navigate(editConference(c)){text(c.name)}}
        table {
          derive viewRows from c
        }
      } 
    }
    
    define page pcInvitation(pcInv : PcInvitation) {
      main()
      define contextSidebar() {
        pcInvitationOperations(pcInv)
      }
      define body() {
        header{"Invitation for Program Committee"}
        table {
          derive viewRows from pcInv
        }
      } 
    }
    
    define page bid(b : Bid) {
      main()
      define contextSidebar() {
        bidOperations(b)
      }
      define body() {
        header{"Bid on " output(b.paper)}
        table {
          derive viewRows from b for (category)
        }
      } 
    }

    define page review(r : Review) {
      main()
      define contextSidebar() {
        reviewOperations(r)
      }
      define body() {
        header{"Review " output(r.paper.title)}
        table {
          derive viewRows from r
        }
      } 
    }
    
    define page paper(p : Paper) {
      main()
      define contextSidebar() {
        paperOperations(p)
      }
      define body() {
        header{"Paper " output(p.title)}
        table {
          derive viewRows from p
        }
      } 
    }*/