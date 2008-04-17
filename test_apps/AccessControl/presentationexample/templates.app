module templates

section basic templates
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
      listitem{ logout() }
      listitem{ navigate(register()) { "register" } }
    }
  }
  
  define applicationmenu()
  {
    menu{
      menuheader{ "View Member Page" }
      for(m:MemberPage){
        menuitem{ navigate(memberPage(m)){ output(m.owner.username) }}
      }
    }    
    menu{
      menuheader{ "Edit Member Page" }
      for(m:MemberPage){
        menuitem{ navigate(editMemberPage(m)){ output(m.owner.username) }}
      }
    }
    menu{
      menuheader{ "Edit Profile" }
      for(u:User){
        menuitem{ navigate(editUser(u)){ output(u.username) }}
      }
    }   
	
  }
  
  define page register()
  {
    main()
    define body()
    {
      header{"Register"}
      var newUser : User := User { };
      form { 
        table {
          row{ "Username: " input(newUser.username) }
          row{ "Password: " input(newUser.password) }
        }
        action("Create", createUser())
        action createUser() {
          //newUser.viewAccess := priv;
          newUser.password := newUser.password.digest();
          newUser.page:=MemberPage{};
          newUser.persist();
          return home();
        }
      }  
    }
  }
  
