module ac

description {
  Access control rules
}

section access control

access control rules {
  principal is User with credentials username, password
}

access control rules {
  pointcut defaultAccess() { page register(), page signin(), page home(), page allConference() }

  rules pointcut defaultAccess() {
    true
  }
  
  rules page error(msg : String) {
    true
  }

  rules page message(msg : String) {
    true
  }
  
  rules page user(*) {
    true
  }
  
  rules page conference(*) {
    true
  }
  
  rules page createConference() {
    securityContext.principal.isAdmin
  }
  
  rules page editConference(c : Conference) {
    securityContext.principal.isAdmin || securityContext.principal in c.chairs
  }
  
  rules template *(*) {
    true
  }
  
  rules function *(*) {
    true
  }
}
