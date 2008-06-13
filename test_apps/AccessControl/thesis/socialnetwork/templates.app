module templates

section main template.

  define main() 
  {
    block("top") {
      top()
    }

    block("body") {
      block("left_innerbody") {
        sidebar()
      }
      block("main_innerbody") {
        body()
      }
    }

    block("footer") {
      footer()
    }
  }
  define footer(){}
  define top() {
    block("header") {}
    block("menubar") { 

    }
  }

section basic page elements.

  define homesidebar() {
    list { listitem{ navigate("Home", home()) } }
    
  }
  
  define sidebar() {
    list { 

       listitem { navigate(home()) { "Home" } }
       
       listitem { logout() }    
   
       listitem { navigate(editUser(securityContext.principal)){"Edit Profile"} }
       listitem { navigate(friends(securityContext.principal)){"Friends"} }
       listitem { navigate(groups(securityContext.principal)){"Groups"} }
       
       listitem { navigate(register()) { "New Member" } }
    }
  }