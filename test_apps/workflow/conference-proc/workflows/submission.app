module submission

section paper submission

  procedure submitAbstract(c : Conference) {
    when { c.abstractDeadline < now() }
  
  
  }
  
  procedure submitPaper(p : Paper) {
    when { c.paperDeadline < now()
    
  c.finalizePc.performed && !c.stopAcceptingPapers.performed }
    view {
      var paper : Paper := Paper{}
      title{"Submit paper"}
      main()
      define contextSidebar() {
        conferenceProcedures(c)
      }
      define body () {
        section(){
          header{"Submit paper"}
          form {
            table {
              derive editRows from paper for (title, abstract, authors)
            }
            action("Save", do()){}
            action("Cancel", cancel()){}
          }
        }
        action cancel ( )
        {
          return home();
        }
      }
    }
    do {
      paper.conference := c;
      paper.save();
    }
  }