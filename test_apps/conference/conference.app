application com.example.conference

description {
  Conference application
}

imports data
imports user
imports templates
imports ac
imports initialize
imports pcinvite

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
    (c.stage = assemblePC && (securityContext.principal.isAdmin || securityContext.principal in c.chairs || securityContext.principal in c.pc))
    ||
    (c.stage = acceptingPapers)
    ||
    (c.stage = reviewing)
  }
}

define page conference(c : Conference) {
  main()
  title{"Conference: " output(c.name) }
  define body() {
    header{output(c.name)}
    if(c.stage = assemblePC) {
      section {
        header{"Assembling PC"}
        "Current invites:"
        list {
          for(inv : PcInvite in c.pcInvitesList) {
            listitem {
              output(inv.user.name) "(Accepted yet: " output(inv.accepted) ")" 
              if(!inv.accepted && inv.reason != "") {
                "Reason: " output(inv.reason)
              }
            }
          }
        }
        "Add member:"
        var pcInvite : PcInvite := PcInvite{ conference := c }
        var name : String
        var email : Email
        form {
          table {
            row { "Name:" input(name) }
            row { "Email:" input(email) }
          }
          action("Invite", invite())
          action invite() {
            pcInvite.user := getUser(name, email);
            pcInvite.persist();
            c.pcInvites.add(pcInvite);
            var pcTask : PcInviteTask := PcInviteTask { assignee := pcInvite.user invite := pcInvite };
            pcTask.persist();
            //email(inviteMail(pcInvite));
            return conference(c);
          }
        }
      }
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