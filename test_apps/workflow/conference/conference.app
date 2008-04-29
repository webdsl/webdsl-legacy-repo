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
  /*
    define conferenceOperations(c : Conference) {
      
    }
    */
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