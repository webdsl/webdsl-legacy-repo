module accesscontrol

section ac stuff.
  
  entity ViewMode{
    name :: String
  }
  globals{
    var pub  : ViewMode := ViewMode{name:="public"};
    var fri  : ViewMode := ViewMode{name:="friends"};
    var priv : ViewMode := ViewMode{name:="private"};
  }
  extend entity MemberPage {
    viewAccess :: ViewMode
  }

  
access control rules {
  principal is User with credentials username,password

  predicate isViewable(mp:MemberPage)
  {
       mp.viewAccess = pub
    || mp.viewAccess = fri && securityContext.principal in mp.owner.friends
    || mp.viewAccess = priv && securityContext.principal = mp.owner 
  }
  
  rules page memberPage(mp:MemberPage)
  {
    isViewable(mp)
  }
  
  rules page editMemberPage(mp:MemberPage)
  {
    securityContext.principal = mp.owner
  }
  
  rules page editUser(u:User)
  {
    securityContext.principal = u
  }
  
  rules page user(u:User)
  {
    securityContext.principal = u
  }
  
  pointcut public()
  {
    page home(),
    template *(*)
  }
  rules pointcut public()
  {
    true
  }
  
  rules template login()
  { 
    !securityContext.loggedIn
  }
  rules template logout()
  { 
    securityContext.loggedIn
  }
  rules page register()
  {
    !securityContext.loggedIn
  }
}
