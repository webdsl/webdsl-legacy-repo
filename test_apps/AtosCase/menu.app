module ui/menu


section menus

  define topmenu() {
    "\n" 
    menubar("horizontal") {
    "\n" 
      menu {
        menuheader{ navigate(home()){"Home"} }
        menuheader{ navigate(nieuwInkomendBericht()){"Nieuw Inkomend Bericht"} }
        menuheader{ navigate(inkomendeBerichten()){"Alle Inkomende Berichten"} }
        menuheader{ navigate(dossierTasks()){"Alle Dossier Taken"} }        
      }
    }
    "\n" 
  }

