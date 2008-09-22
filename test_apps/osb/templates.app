module templates

section main template.

  define main() {
  	top()
  	topmenu()
  	sidebar()
  	body()
  	footer()
  }

section basic page elements.

  define top() {
  	image("/images/osb_logo.png")
  }
  
  define topmenu() {
    mainNavigation()
    adminNavigation()
  }

  define sidebar() {
    sidebarText()
  }
  
  define sidebarText() {
    "Standard sidebartext"
  }
  
  define body() {
  	"Standard body"
  }
  
  define footer() {
  }


section navigation.
  
  define mainNavigation() {
    list {
        listitem { navigate(home()) { "Home" } }
        listitem { navigate(home()) { "UserInfo" } }
        listitem { navigate(home()) { "Reports" } }
    }
  }
  
  define adminNavigation() {
    list {
        listitem { navigate(home()) { "Administration" } }
        listitem { navigate(home()) { "Status" } }
        listitem { navigate(home()) { "Configuration" } }
        listitem { navigate(home()) { "Logout" } }
    }
  }

