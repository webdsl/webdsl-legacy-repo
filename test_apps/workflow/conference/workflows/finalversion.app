module workflows/finalversion

operations final version

  workflow editFinalVersionWorkflow(p : Paper) {
    init() {
      
    }
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
    when { !p.conference.conferenceWorkflow.done && p.editFinalVersionWorkflow.started && !p.finalize.performed }
  }