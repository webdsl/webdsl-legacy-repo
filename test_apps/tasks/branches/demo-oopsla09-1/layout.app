module layout

section application layout

define main() {
  includeCSS("webtasks.css")
  block[id:=pagewrapper] {
    block[id:=header]{ par{ "Web Tasks" } }
    block[id:=navbar]{
      list{
        listitem{ navigate(root()){"Home"} }
        listitem{ navigate(register()){"Register"} }
        //if(!loggedIn()) { 
          //listitem{ navigate(signin()){"Sign in"} }
        //} else {
          //logout()
        //}
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

section customize validation template

define ignore-access-control errorTemplateInput(messages : List<String>){
  block()[style := "border: 1px solid #FF0000; margin-left: -5px; margin-top: 5px; margin-bottom: 5px; padding: 4px"]{
    validatedInput
    for(ve: String in messages){
      block()[style := "width:100%; color: #FF0000; margin-top: 5px;"]{
        output(ve)
      }     
    }
  }
}

define page accessDenied() {
  main{ par{ "That page does not exist or you don't have permission to view it." } }
}

// define page signin() { main{ par{ login() } }  }