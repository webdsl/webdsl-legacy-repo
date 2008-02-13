module users/access-control

  note {
  
    - policy for creation of groups
  
    - policy for assigning members to groups
  
    - policy for assigning groups to topics and webs
  
  }
  
access control policy
  anonymous OR admin

section admin ac

  access control rules admin {
    pointcut admin()
    {
      page *(*),
      template *(*),
      function *(*)
    }
   
    rules pointcut admin() {
      securityContext.principal in adminGroup.members
    }  
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
      if (user = null || xs = null || xs.length = 0) { return false; }
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
  
    rules template *(*) {
      true
    }
    
    rules function *(*) {
      true
    }
  
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
      securityContext.principal in adminGroup.members
    }
    
    rules page editProfile(u : User) {
      securityContext.principal = u
      || securityContext.principal in adminGroup.members
    }
    
    rules page register() {
      true
    }
    
    rules page pendingRegistrations() {
      securityContext.principal in adminGroup.members
    }
    
    rules page registrationPending(*) {
      true
    }
    
    rules template adminMenu() {
      securityContext.principal in adminGroup.members
    }

    rules page configuration(*) {
      isAdministrator()
    }

    rules page editConfiguration(*) {
      isAdministrator()
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
  
  rules page groups() {
    true
  }
  
  rules template userGroupOperationsMenu(g:UserGroup)
  {
    !(securityContext.principal in g.members)
    && !(securityContext.principal in g.requested)
  }
  
  rules template modGroupOperationsMenu(g:UserGroup)
  {
    securityContext.principal in g.moderators
  }
  
  rules page userGroup(g : UserGroup) {
    securityContext.loggedIn
  }
    
  rules page editUserGroup(g : UserGroup) {
    securityContext.principal in g.moderators
  }
    
    rules template joinGroup(g : UserGroup) {
      !(securityContext.principal in g.members)
      && !(securityContext.principal in g.requested)
    }
    
    rules page membershipRequests(g : UserGroup) {
      securityContext.principal in g.moderators
    }
    
    rules template editPermissions(acl : ACL, aclSuper : ACL) {
      memberOf(acl.moderate, securityContext.principal)
      || (acl.moderate.length = 0 
          && memberOf(aclSuper.moderate, securityContext.principal))
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
    
    rules page changeRole() {
      securityContext.loggedIn      
    }
    
  }
