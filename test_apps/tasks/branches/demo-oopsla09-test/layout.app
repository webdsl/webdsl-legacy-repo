module layout

define main() {
  includeCSS("webtasks.css")
  block[id:=pagewrapper] {
    block[id:=header]{ par{ "Web Tasks" } }
    block[id:=navbar]{
      list{
        listitem{ navigate(root()){"Home"} }
        if(!loggedIn()) { 
          listitem{ navigate(register()){"Register"} }
          listitem{ navigate(signin()){"Sign in"} }
        } else {
          logout()
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