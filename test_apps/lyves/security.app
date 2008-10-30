module security

access control rules
{
  principal is User with credentials nick, password


  pointcut openSections(){
    page home(),
    page signup(),
    template inline*(*), //hack!
    template popup*(*),
    template main(),
    template topheader(),
    template topmenu(),
    template quickpane(),
    template body(),
    template homebody(),
    template quicksearch(),
    template loginlogout(),
    template login()
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
