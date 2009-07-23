module frame/main

  define main() 
  {
    title{"Inventory"}
    top()
    sitemenu()
    messages()
    body()
    footer()
  }
  
  define body(){
    "..."
  }
  
  define sitemenu(){
    navigate(home()){"Home"}
    "  |  "
    navigate(createUser()){"Add User"}
    "  |  "
    navigate(listUsers()){"List Users"}
  }

  define top() {
    navigate(home()){
      image("/images/logosmall.png")
    }
    topRight()
  }
  
  define topRight(){
    navigate(login()){"Login"}
    navigate(logout()){"Logout"}
  }
  
  define footer() {
    leftFooter()
    rightFooter()
  }

  define leftFooter(){
    //navigate(home()) { "About WebDSL" }
  }
  
  define rightFooter(){
    "powered by "
    navigate(url("http://www.webdsl.org")){"WebDSL"}
    //" and "
    //navigate(url("http://www.strategoxt.org")){"Stratego/XT"}
  }
