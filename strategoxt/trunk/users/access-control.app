module users/access-control

  description {
  
    - policy for creation of groups
  
    - policy for assigning members to groups
  
    - policy for assigning groups to topics and webs
  
  }
  
access control policy

  anonymous OR admin



  access control rules admin
  
    pointcut admin()
    {
      page *(*),
      template *(*)
    }
   
    rules pointcut admin() {
      isAdministrator()
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
     
  //predicate memberOf(gs : Set<UserGroup>) {
  //  Or[g in gs | g : UserGroup in principal.activeGroups]
  //}
  
    function memberOf(xs : Set<UserGroup>) : Bool { 
      if (securityContext.principal = null || xs = null || xs.length = 0) { return false; }
      else {
        for(y : UserGroup in securityContext.principal.activeGroups) { 
          if(y in xs) { return true; }
        }
        return false;
      }
    }
    
 
    
  }
    

section users

  access control rules { // user profiles
  
    rules template *(*) {
      true
    }
    

    predicate isAdministrator() {
      securityContext != null && securityContext.principal != null &&
      adminGroup in securityContext.principal.activeGroups
    }
    
    predicate isWebCreator() {
      securityContext != null && securityContext.principal != null &&
      webCreateGroup in securityContext.principal.activeGroups
    }
    
    rules page user(*) {
      true
    }
    
    rules page users() {
      true
    }
    
    rules page editUser(u : User) {
      isAdministrator()
    }
    
    rules page editProfile(u : User) {
      securityContext.principal = u
    }
    
    rules template thisUserMenu(u : User) {
      isAdministrator()
    }
    
  }
  
  access control rules { // registration
  
    rules page register() {
      true
    }
    
    rules page pendingRegistrations() {
      isAdministrator()
    }
    
    rules page registrationPending(*) {
      true
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
      isAdministrator()
    }
    
    rules page login() {
      true
    }
  }
  
section groups

access control rules { // groups
  
  rules page groups() {
    true
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
    memberOf(acl.moderate) 
    || (acl.moderate.length = 0 
        && memberOf(aclSuper.moderate)) 
  }
    
  predicate mayEditACL(acl : ACL, aclSuper : ACL) {
    memberOf(acl.moderate) 
    || (acl.moderate.length = 0 
        && memberOf(aclSuper.moderate))
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
    
    rules page changeActiveGroups() {
      securityContext.loggedIn      
    }
    
  }
