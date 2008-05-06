module workflows/finalversion

operations final version

  workflow editFinalVersionWorkflow(p : Paper) {
    done { p.finalize.performed }
  }
  
  operation editFinalPaper(p : Paper) {
    who { securityContext.principal in p.authors }
    when { !p.conference.conferenceWorkflow.done && p.editFinalVersionWorkflow.started && !p.finalize.performed }
    view {
      title{"Edit paper"}
      derive editPage from p
    }
  }
  
  operation finalize(p : Paper) {
    who { securityContext.principal in p.authors }
<<<<<<< HEAD:workflow/conference/workflows/finalversion.app
    when { !p.conference.conferenceWorkflow.done && p.editFinalVersionWorkflow.started && !p.finalize.performed }
  }
=======
    when { p.editFinalVersionWorkflow.started && !p.finalize.performed }
  }
>>>>>>> Removed some unnecessary code.:workflow/conference/workflows/finalversion.app
