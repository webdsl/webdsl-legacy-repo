module security

access control rules
{
  principal is User with credentials nick, password


  pointcut openSections(){
    page home(),
    page signup(),
    template *(*)
  }
  rules pointcut openSections(){
    true
  }

  pointcut closedSections(u: User) {
    page viewUser(u),
    template quickviewUser(u) 
  } 
  
  rules pointcut closedSections(u: User) {
    mayViewUser(u)
  } 
  
  predicate mayViewUser (u:User){
    securityContext.principal in u.friends || u.public == true || securityContext.principal.moderator == true
  }
 
}
