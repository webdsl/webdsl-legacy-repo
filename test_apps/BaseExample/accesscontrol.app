module accesscontrol

section AccessControl Declarations

  define allowedUsersRow(document:Document) {
    row{ "Allowed Users:" input(document.allowedUsers) }
  }

  define login() {
    var usr : User := User{};
    form { 
      table {
        row{ "Name: " input(usr.name) }
        row{ "Password: " input(usr.password) }
        row{ action("Sign in", signin()) "" }
      }
      action signin() {
        var users : List<User> :=
          select u from User as u 
          where (u._name = ~usr.name);
  
        for (us : User in users ) {
          if (us.password.check(usr.password)) {
            securityContext.principal := us;
            securityContext.loggedIn := true;
            return viewUser(securityContext.principal);
          }
        }
        securityContext.loggedIn := false;
        return home();
      }
    }
  }

  define logout()
  {
    "Logged in as " output(securityContext.principal)
    form {
      actionLink("Log Out", logoff())
      action logoff() {
        securityContext.loggedIn := false;
        securityContext.principal := null;

        return home();
      }
    }
  }

  extend entity Document
  {
    allowedUsers -> Set<User>
  }

  access control rules
  {
    principal is User with credentials name, password

    pointcut openSections() {
      page home(),
      template login(),
      template logout(),
      page createDocument(),
      page createUser(),
      page viewUser(*)
      
      //template sidebar(), not needed template not denied by default
      //page allDocument(),   not used
      //page user(*),
      //page allUser()
    }

    rules pointcut openSections() {
      true
    }

    //viewing of document
    predicate mayViewDocument (u:User, d:Document) {
      d.author = securityContext.principal
      || u in d.allowedUsers
    }
    rules page viewDocument(d:Document) {
      mayViewDocument(securityContext.principal,d)
    }
    
    //edit document, select users that can view
    rules page editDocument(d:Document) {
      d.author = securityContext.principal
      //not necessary but nice for completeness
      rules action save() {
        d.author = securityContext.principal
      }
      rules action cancel() {
        d.author = securityContext.principal
      }      
    }
    
    //hide login / logout if needed
    rules template login() {
      !securityContext.loggedIn
    }
    rules template logout() {
      securityContext.loggedIn
    }
    
    //only edit own page
    rules page editUser(u:User) {
      u = securityContext.principal     
    }
  }