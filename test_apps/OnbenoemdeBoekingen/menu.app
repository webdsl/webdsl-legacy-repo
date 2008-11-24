module menu


section menus

  define topmenu() {
    "\n" 
    menubar {
    "\n" 
      menu {
        menuheader{ navigate(home()){"Home"} }
      }
      menu {
        menuheader{ "Coordinator" }
        menuitem { navigate(nieuweInvoer()) { "Nieuwe bron invoer" } }
        menuitem { navigate(invoerNaarRegistratie()) { "Bron invoer in registratie bestand" } }
      }
      menu {
        menuheader { navigate(alleRegistraties()) { "Registraties" } }
      }
    }
    "\n" 
  }

