module workflows/finalversion

section final version

  procedure editFinalVersion(p : Paper) {
    process{
      repeat{ editFinalPaper(p) };
      finalize(p)
    }
  }
  
  procedure editFinalPaper(p : Paper) {
    who { securityContext.principal in p.authors }
    view {
      title{"Edit paper"}
      derive editPage from p
    }
  }
  
  procedure finalize(p : Paper) {
    who { principal in p.authors }
  }
