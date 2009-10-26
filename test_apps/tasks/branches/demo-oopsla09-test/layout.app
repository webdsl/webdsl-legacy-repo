module layout

define main() {
  includeCSS("webtasks.css")
  block[id:=pagewrapper] {
    block[id:=header]{ par{ "Web Tasks" } }
    block[id:=navbar]{
      menubar{
        menu{ menuheader{ navigate(root()){"Home"} } }
        if(!loggedIn()) { 
          menu{ menuheader{ navigate(register()){"Register"} } }
          menu{ menuheader{ navigate(signin()){"Sign in"} } }
        } else {
          menu{ menuheader{ logout() } }
        }
      }
    }
    block[id:=content]{
      messages()
      elements()
    }
    block[id:=footer]{
      par{ "powered by " navigate(url("http://webdsl.org")){"WebDSL"} }
    }
  }
}
  
define page accessDenied() {
  main{ par{ "That page does not exist or you don't have permission to view it." } }
}

define page signin() { 
  main{ par{ login() } } 
}