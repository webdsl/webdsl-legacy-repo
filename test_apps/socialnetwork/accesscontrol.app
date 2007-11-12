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
    (u.viewAccess = pub)
    || (u.viewAccess = fri && securityContext.principal in u.friends)
    || (u.viewAccess = priv && securityContext.principal = u)
  }

  rules page viewUser(u : User)
  {
    viewAllowed(u)
    rules template div("owner")
    {
       securityContext.principal = u
    } 
  }
  
  predicate groupViewAllowed(ug : UserGroup){
    (ug.viewAccess = pub) 
    || (ug.viewAccess = mem && securityContext.principal in ug.members)
    || (ug.viewAccess = priv && securityContext.principal = ug.owner)
  }
  
  rules page viewUserGroup(ug : UserGroup)
  {
    groupViewAllowed(ug)
    rules template div("ownermod")
    {
       securityContext.principal = ug.owner || securityContext.principal in ug.moderators
    } 
  } 
  
  pointcut groupediting
  {
    template group*,
    page editUserGroup
  }
  rules pointcut groupediting(ug : UserGroup)
  {
    securityContext.principal = ug.owner || securityContext.principal in ug.moderators 
  }
  
  pointcut grouppageediting
  {
    template groupPageEdit,
    page editGroupPage
  }
  rules pointcut grouppageediting(p:GroupPage)
  {
    securityContext.principal = p.group.owner || securityContext.principal in p.group.moderators
  }
  
  pointcut public
  {
    page home,
    page register
  }
  rules pointcut public()
  {
    true
  }
  
  pointcut pageediting
  {
    page editPage,
    template homePageEdit
  } 
  rules pointcut pageediting(p: Page)
  {
    p.owner = securityContext.principal
  }
  
  pointcut userediting 
  {
    template userEdit,
    page editUser,
    template friend*,
    template group*,
    page groups,
    page friends
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
  rules template div("loggedIn")
  {
    securityContext.loggedIn
  }

}
