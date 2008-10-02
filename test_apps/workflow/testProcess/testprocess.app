application org.webdsl.testProcess


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports ac
imports layout
imports style

section procedures

/*  auto procedure pdpWorkflow(p : PdpMeeting) {
    process {
      (employeeFillInForm(p) and managerFillInForm(p));
      repeat {
        writeReport(p);
        approveReport(p)
      } until finalizeReport(p)
    }
  }*/

/*  auto procedure pdpWorkflow(p : PdpMeeting) {
    process {
      (employeeFillInForm(p) ; managerFillInForm(p))
      +
      (writeReport(p) ; approveReport(p)) 
    }
  }*/

/*  auto procedure pdpWorkflow(p : PdpMeeting) {
    process {
      while (p.report != "Dude") {
        writeReport(p) ; managerFillInForm(p)
      }
    }
  }*/
  
/*  auto procedure pdpWorkflow(p : PdpMeeting) {
    process {
      writeReport(p) ; 
      if (p.report != "Dude") {
        managerFillInForm(p)
      }
    }
  }*/


  procedure pdpWorkflow(p : PdpMeeting) {
    do {
      p.report := "Kanarie";
      p.persist();
    }
    process {
      (employeeFillInForm(p) and managerFillInForm(p));
      repeat {
        writeReport(p);
        approveReport(p)
      } until {finalizeReport(p)}
    }
  }
  
  procedure employeeFillInForm(p : PdpMeeting) {
    who { securityContext.principal == p.employee }
    view {
      derive procedurePage from p for (employeePreparation) {
        title{"Fill in employee form"}
        header{"Fill in employee form"}
      }
    }
  }

  procedure managerFillInForm(p : PdpMeeting) {
    who { securityContext.principal == p.employee.manager }
    view {
      title{"Fill in manager form"}
      derive procedurePage from p for (managerPreparation) {
        title{"Fill in manager form"}
        header{"Fill in manager form"}
      }
    }
  }

  procedure writeReport(p : PdpMeeting) {
    who { securityContext.principal == p.employee.manager }
    view {
      derive procedurePage from p for (report) {
        title{"Write report"}
        header{"Write report"}
      }
    }
  }

  procedure finalizeReport(p : PdpMeeting) {
    who { securityContext.principal == p.employee.manager }
  }

  procedure approveReport(p : PdpMeeting) {
    who { securityContext.principal == p.employee }
  }
  
  auto procedure testRecursiveWorkflow(t : TestRecursive) {
    process {
      proc1(t)
      ; if (t.child != null) {
          testRecursiveWorkflow(t.child)
        }
      ; proc2(t)
    }
  }
  
  procedure proc1(t : TestRecursive) {
    view {
      derive procedurePage from t for (proc1Text) {        
        title{"Fill proc1 for " text(t.name)}
        header{"Fill proc1 for " text(t.name)}
      }
    }
  }
  
  procedure proc2(t : TestRecursive) {
    view {
      derive procedurePage from t for (proc2Text) {
        title{"Fill proc2 for " text(t.name)}
        header{"Fill proc2 for " text(t.name)}
      }
    }
  }
      
  
section pages

  globals {
    var test3: TestRecursive;
  }

  define body() {}

  define page home() {
    main()
    define body() {
      var employee : User
      header { "Home" }
      section {
        form {
          header{"Quick start PDP Meeting for employee"}
          action("Quick start", quickStart())
        
          action quickStart() {
/*            test3 := newTestRecursive();
            test3.name := "Bovenste";
            test3.child := newTestRecursive();
            test3.child.name := "Middelste";
            test3.child.child := newTestRecursive();
            test3.child.child.name := "Onderste";
            test3.child.child.persist();
            test3.child.persist();
            test3.persist();
            test3.testRecursiveWorkflow.enable();*/
            
            var p : PdpMeeting := newPdpMeeting();
            p.employee := aUser;
            p.persist();
            p.pdpWorkflow.enable();
            
            if (securityContext.loggedIn) {
              return message("Workflows started!");
            } else {
              return signin();
            }
          }
        }
      }
      
      section {
        form {
          header{"Organize PDP Meeting"}
          "For: " input(employee)
          action("Organize", organize())

          action organize() {
            var p : PdpMeeting := newPdpMeeting();
            p.employee := employee;
            p.persist();
            p.pdpWorkflow.enable();

            if (securityContext.loggedIn) {
              return message("Workflows started!");
            } else {
              return signin();
            }
          }
        }
      }
    }
  }
  
  define page allMeetings() {
    main()
    define body() {
      for(p : PdpMeeting) {
        text(p.name)
        text(" ")
      }
    }
  }

access control rules 
  rule page pdpMeeting(pdpMeeting : PdpMeeting) {
    securityContext.principal == pdpMeeting.employee || securityContext.principal == pdpMeeting.employee.manager
  }
  rule page testRecursive(t : testRecursive) {
    true
  }
    
section pages

  // patch since no auto generation of view pages
  define page pdpMeeting(p : PdpMeeting) {
    derive procedureViewPage from p
  }
  define page user(u : User) {
    derive viewPage from u
  }
  define page testRecursive(t : TestRecursive) {
    derive procedureViewPage from t
  }

