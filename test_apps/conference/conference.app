application com.example.conference

description {
  Conference application
}

imports data
imports ac
imports user
imports paper
imports initialize
imports pcinvite
imports submitpapers
imports bid
imports reviewassignment
imports review
imports decideacceptance
imports submitfinalpapers
imports templates

section utilities

  globals {
    function getUser(name : String, email : Email) : User {
      var u : User;
      var users : List<User> :=
            select u from User as u 
            where (u._email = ~email);
      if(users.length = 0) {
        u := User { name := name email := email registered := false };
        u.persist();
      } else {
        for(eu : User in users) { // HACK!
          u := eu;
        }
      }
      return u;
    }
  }

section pages

  define page home() {
    main()
    define body() {
      list {
        listitem { navigate(allConference()) { "All conferences" } }
        listitem { navigate(createConference()) { "Create conference" } }
        listitem { navigate(createPaper()) { "Submit paper" } }
        listitem { navigate(createReview()) { "Write review" } }      
      }                        
    }
  }

  define page createConference() {
    main()
    title{"Start new conference"}
    define body() {
      header{"Start new conference"}
      var c : Conference := Conference { stage := assemblePC }
      form {
        table {
          row { "Name:" input(c.name) }
          row { "Chairs: " input(c.chairs) }
        }
        action("Create", createConference())
        
        action createConference() {
          c.persist();
          initPcInviteStage(c);
          return conference(c);
        }
      }
    }  
  }

  access control rules {
    rules page conference(c : Conference) {
      c.stage != assemblePC || securityContext.principal.isAdmin || securityContext.principal in c.chairs
    }
  }

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
        header{"Program Comittee"}
        list {
          for(u : User in c.pcList) {
            listitem { output(u) }
          }
        }
        submittedPapers(c)
        par { navigate(submitPaper(c)) { "Submit paper" } }
      }
    }  
  }

  access control rules {
    rules template submittedPapers(c : Conference) {
      (c.stage != conferenceCompleted && (securityContext.principal.isAdmin || securityContext.principal in c.chairs || securityContext.principal in c.pc))
      ||
      (c.stage = conferenceCompleted)
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
  }

/*
  define email inviteMail(invite : PcInvite) {
    to(invite.user.email)
    subject("Invite to join PC of "+invite.conference.name)
    from("admin@webdsl.org")
    body {
      par{ "Dear " output(invite.user.name) ","}
      par{ "We would like to invite you to join the PC of " output(invite.conference.name) }
      par{ "Please confirm or reject this request at " 
           navigate(tasks(invite.user)){url(tasks(invite.user))}
      }
      par { "Best," }
      par { "The " output(invite.conference.name) " Team" }
    }
  }
*/

  define page error(msg : String) {
    main()
    title{"Error"}
    define body() {
      header{"Error"}
      output(msg)
    }
  }

  define page message(msg : String) {
    main()
    title{"Message"}
    define body() {
      header{"Message"}
      output(msg)
    }
  }
