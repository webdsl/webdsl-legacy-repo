module templates

section MAC templates
  define main() 
  {
    block("top"){ top() }
    block("body"){
      block("left_innerbody"){ sidebar() }
      block("main_innerbody"){ body() }
    }
    block("footer"){ footer() }
  }
  define footer(){}
  define top(){
    block("header"){}
    block("menubar"){ menubar{ applicationmenu() }}
  }
  define body(){}
  define sidebar(){
    list{
      listitem{ navigate(home()){ output("home") }}
    }
  }
  
  define applicationmenu()
  {
    menu{
      menuheader{ "View Document" }
      for(d:Document){
        menuitem{ navigate(viewDocument(d)){ output(d.title) }}
      }
    }
    menu{
      menuheader{ "Edit Document" }
      for(d:Document){
        menuitem{ navigate(editDocument(d)){ output(d.title) }}
      }
    }    
    menu{ menuheader{ navigate(createDocument()){ output("New document") }}}  
    menu{
      menuheader{ "View User" }
      for(u:User){
        menuitem{ navigate(user(u)){ output(u.name) }}
      }
    }
    menu{
      menuheader{"Edit User"}
      for(u:User){
        menuitem{ navigate(editUser(u)){ output(u.name) }}
      }
    }    
    menu{ menuheader{ navigate(createUser()){ output("New user") }}}  
  }

  
