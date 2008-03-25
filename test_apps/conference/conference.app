application com.example.conference

description {
  Conference application
}

imports data
imports ac
imports user
imports initialize
imports pcinvite
imports paper
imports bid
imports reviewassignment
imports templates

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
    var conf : Conference := Conference { stage := assemblePC }
    form {
      table {
        row { "Name:" input(conf.name) }
        row { "Chairs: " input(conf.chairs) }
      }
      action("Create", createConference())
      
      action createConference() {
        conf.persist();
        return conference(conf);
      }
    }
  }  
}

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

access control rules {
  rules page conference(c : Conference) {
    (c.stage = assemblePC && securityContext.loggedIn && (securityContext.principal.isAdmin || securityContext.principal in c.chairs || securityContext.principal in c.pc))
    ||
    (c.stage = acceptingPapers)
    ||
    (c.stage = bidOnPapers && securityContext.loggedIn && (securityContext.principal.isAdmin || securityContext.principal in c.chairs || securityContext.principal in c.pc))
    ||
    (c.stage = decideReviewAssignment && securityContext.loggedIn && (securityContext.principal.isAdmin || securityContext.principal in c.chairs))
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
    if(c.stage = assemblePC) {
      assemblePCView(c)
    }
    if(c.stage = acceptingPapers) {
      acceptingPapersView(c)
    }
    if(c.stage = bidOnPapers) {
      biddingView(c)
    }
    if(c.stage = decideReviewAssignment) {
      decideReviewAssignment(c)
    }
  }  
}

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
