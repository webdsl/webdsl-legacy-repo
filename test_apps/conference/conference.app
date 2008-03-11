application com.example.conference

description {
  Conference application
}

imports data
imports templates
imports ac
imports initialize

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

define page signin() {
  main()
  title{"Sign in"}
  define body() {
    var username : String;
    var password : Secret;
    form { 
      table {
        row{ "Username: " input(username) }
        row{ "Password: " input(password) }
        row{ action("Sign in", signin()) "" }
      }
      action signin() {
        var users : List<User> :=
          select u from User as u 
          where (u._username = ~username);
  
        for (us : User in users ) {
          if (us.password.check(password)) {
            securityContext.principal := us;
            securityContext.loggedIn := true;
            return user(securityContext.principal);
          }
        }
        securityContext.loggedIn := false;
        return error("Wrong combination of username/password");
      }
    }
    par{ navigate(register()){"Register new user"} }
  }
}

define page register() {
  main()
  title{"Register new user"}
  define body() {
    section {
      header{"Register New User"}
      
      var newUser : User := User { };
      form { 
        table{
          row { "Username" input(newUser.username) }
          row { "Full name" input(newUser.name) }
          row { "Email" input(newUser.email) }
          row { "Password" input(newUser.password) }
        }
        captcha()
        action("Register", createUser())
        action createUser() {
          var users : List<User> :=
            select u from User as u 
            where (u._username = ~newUser.username) or (u._email = ~newUser.email);
            
          if (users.length > 0) {
            return error("Username or email address already in use");
          } else {  
            newUser.password := newUser.password.digest();
            newUser.persist();
            return signin();
          }
        }
      }
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

access control rules {
  rules page conference(c : Conference) {
    (c.stage = assemblePC && (securityContext.principal.isAdmin || securityContext.principal in c.chairs))
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
          for(inv : PCInvite in c.invitesList) {
            listitem { output(inv.name) "(Status: " output(inv.accepted) ")" }
          }
        }
        "Add member:"
        var pcInvite : PCInvite := PCInvite{ conference := c accepted := null }
        form {
          table {
            row { "Name:" input(pcInvite.name) }
            row { "Email:" input(pcInvite.email) }
          }
          action("Invite", invite())
          action invite() {
            email(inviteMail(pcInvite));
            return conference(c);
          }
        }
      }
    }
  }  
}

define email inviteMail(invite : PCInvite) {
  to(invite.email)
  subject("Invite to join PC of "+invite.conference.name)
  from("admin@webdsl.org")
  body {
    par{ "Dear " output(invite.name) ","}
    par{ "We would like to invite you to join the PC of " output(invite.conference.name) }
    par{ "Please confirm or reject this request at " 
         navigate(confirmPCInvite(invite)){url(confirmPCInvite(invite))}
    }
    par { "Best," }
    par { "The " output(invite.conference.name) " Team" }
  }
}

define page confirmPCInvite(invite : PCInvite) {
  title {"Confirm PC invite"}
  define body() {
    header{"Confirm PC Invite"}
    form {
      "Optional reason:" input(invite.reason)
      action("Accept", accept())
      action("Reject", reject())
      
      action accept() {
        invite.accepted := true;
        return home();
      }
      
      action reject() {
        invite.accepted := false;
        return home();
      }
    }
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
