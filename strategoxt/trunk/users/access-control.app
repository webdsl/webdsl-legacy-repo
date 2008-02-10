module users/access-control

  note {
  
    - policy for creation of groups
  
    - policy for assigning members to groups
  
    - policy for assigning groups to topics and webs
  
  }
  
section ac policies

  globals {
    
    function containsOneOf(xs : Set<UserGroup>, ys :  Set<UserGroup>) : Bool {
      for(y : UserGroup in ys) {
        if(y in xs) { return true; }
      }
      return false;
    }
    
    // note: this should be defined using true generics
   
    function memberOf(xs : Set<UserGroup>, user : User) : Bool {
      if (xs.length = 0) { return false; }
      else {
        for(y : UserGroup in user.groups) {
          if(y in xs) { return true; }
        }
        return false;
      }
    }
    
  }
  
section users

  access control rules {
  
    //predicate memberOf(xs : Set<UserGroup>) {
    //  memberOfAux(xs, securityContext.principal)
    //}
  
    predicate isAdministrator() {
      securityContext.principal in adminGroup.members
    }
    
    // note: this can be changed into a check against the 'active groups'
    // of the principal in order to instigate a sort of role-based access
    // control where not all roles are always active
    
    rules page user(*) {
      true
    }
    
    rules page users() {
      true
    }
    
    rules page editUser(u : User) {
      securityContext.principal = u
    }
    
    rules page register() {
      true
    }
    
    rules page pendingRegistrations() {
      securityContext.loggedIn
    }
    
    rules page registrationPending(*) {
      true
    }
    
    rules page changePassword() {
      securityContext.loggedIn
    }
    
    rules page userRegistration(*) {
      securityContext.loggedIn
    }
    
    rules page confirmEmail(*) {
      true
    }
    
    rules template showUserRegistration(*) {
      securityContext.loggedIn
    }
    
    rules page login() {
      true
    }
  }
  
section groups

  access control rules {
  
    rules page userGroup(g : UserGroup) {
      securityContext.loggedIn
      //securityContext.principal in g.members
    }
    
    rules page editUserGroup(g : UserGroup) {
      securityContext.loggedIn
      //securityContext.principal in g.moderators
    }

  }
  
section authentication actions

  access control rules {
  
    rules template signin() {
      !securityContext.loggedIn
    }
    
    rules template signinMenu() {
      !securityContext.loggedIn
    }
    
    rules template signoff() {
      securityContext.loggedIn
    }
    
    rules template signoffMenu() {
      securityContext.loggedIn
    }
    
    rules template signoffAction() {
      securityContext.loggedIn
    }
    
  }
