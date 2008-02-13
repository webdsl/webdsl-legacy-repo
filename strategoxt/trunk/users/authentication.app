module users/authentication

section authentication

  access control rules {
    principal is User with credentials username, password
  }

  define currentUser() {
    signinMenu()
    signoffMenu()
  }
  
  define page login() {
    main()
    define body() {
      signin()
      signoff()
    }
  }

  define signinMenu() {
    menu{ 
      menuheader{ navigate(login()){"Sign in"} }
      menuitem{ navigate(register()){"Register"} }
    }
  }
    
  define signin() 
  {
    var username : String;
    var password : Secret;
    var group    : UserGroup;
    form { 
      table {
        row{ "Username: " input(username) }
        row{ "Password: " input(password) }
      }
      par{ action("Sign in", signin()) }
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
        return home();
      }
    }
    par{ navigate(register()){"Register new user"} }
  }

  define signoff()
  {
    "Signed in as " output(securityContext.principal)
    signoffAction()
  }
  
  define signoffAction() {
    form {
      actionLink("Sign Off", signoff())
      action signoff() {
        securityContext.loggedIn := false;
        securityContext.principal := null;
        // ensure that principal is null when signing off
        return home();
      }
    }
  }
    
  define signoffMenu() 
  {
    menu{
      menuheader{ "You" }
      menuitem{ output(securityContext.principal) }
      menuitem{ signoffAction() }
      menuitem{ navigate(editProfile(securityContext.principal)){"Edit Profile"} }
      menuitem{ navigate(changePassword()){"Change Password"} }
      menuitem{ navigate(changeRole()){"Change Role"} }
      // There's some problems with this, so disabling for now
      //menuitem{ navigate(editUser(securityContext.principal)){"Edit Profile"} }
    }
  }
  
  
section active groups 1

  extend entity User {
    activeGroups -> Set<UserGroup>
  }
  
section active groups 2

  
  define page changeRole()
  {
    main()
    define body() {
      section{
        header{"Change Role"}
        
        par{"Choose groups that you want to be active in"}
        
        form {
          select(securityContext.principal.activeGroups 
                 from securityContext.principal.groups)
          action("Select Groups", selectGroups())
        }
        
        action selectGroups() {
          return user(securityContext.principal);
        }
      }
    }
  }