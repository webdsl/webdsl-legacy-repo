module accesscontrol

section ac stuff.
  
  entity ViewMode{
    name :: String
  }
  globals{
    var pub : ViewMode := ViewMode{name:="public"};
    var fri : ViewMode := ViewMode{name:="friends"};
    var priv : ViewMode := ViewMode{name:="private"};
    var mem : ViewMode := ViewMode{name:="members"};
  }
  extend entity User {
    viewAccess :: ViewMode
  }
  extend entity UserGroup {
    viewAccess :: ViewMode
  }
  
  access control rules {
  principal is User with credentials username,password

  predicate viewAllowed(u:User){
    //securityContext.principal = u
    (u.viewAccess = pub)
    || (u.viewAccess = fri && securityContext.principal in u.friends)
    || (u.viewAccess = priv && securityContext.principal = u)
  }

  rules page viewUser(u : User)
  {
    viewAllowed(u)
  }
  
  predicate groupViewAllowed(ug : UserGroup){
    (ug.viewAccess = pub) 
    || (ug.viewAccess = mem && securityContext.principal in ug.members)
    || (ug.viewAccess = priv && (securityContext.principal = ug.owner || securityContext.principal in ug.moderators))
  }
  
  rules page viewUserGroup(ug : UserGroup)
  {
    groupViewAllowed(ug)
  } 
  
  pointcut groupediting(ug : UserGroup)
  {
    template group*(ug),
    page editUserGroup(ug)
  }
  rules pointcut groupediting(ug : UserGroup)
  {
    securityContext.principal = ug.owner || securityContext.principal in ug.moderators 
  }
  
  pointcut grouppageediting(p:GroupPage)
  {
    template groupPageEdit(p),
    page editGroupPage(p)
  }
  rules pointcut grouppageediting(p:GroupPage)
  {
    securityContext.principal = p.group.owner || securityContext.principal in p.group.moderators
  }
  
  pointcut public()
  {
    page home(),
    page register(),
    template *(*)
  }
  rules pointcut public()
  {
    true
  }
  
  pointcut pageediting(p: Page)
  {
    page editPage(p),
    template homePageEdit(p)
  } 
  rules pointcut pageediting(p: Page)
  {
    p.owner = securityContext.principal
  }
  
  
  pointcut userediting(u:User)
  {
    template userEdit(u),
    page editUser(u),
    template friend*(u),
    template group*(u),
    page groups(u),
    page friends(u)
  }  
  rules pointcut userediting(u:User)
  {
    u = securityContext.principal
  }
  
  rules template login()
  { 
    !securityContext.loggedIn
  }
  rules template logout()
  { 
    securityContext.loggedIn
  }
}
