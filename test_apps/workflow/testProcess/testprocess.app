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

/*  auto procedure testWorkflow(p : PdpMeeting) {
    process {
      (employeeFillInForm(p) and managerFillInForm(p));
      repeat {
        writeReport(p);
        approveReport(p)
      } until finalizeReport(p)
    }
  }*/

/*  auto procedure testWorkflow(p : PdpMeeting) {
    process {
      (employeeFillInForm(p) ; managerFillInForm(p))
      +
      (writeReport(p) ; approveReport(p)) 
    }
  }*/

/*  auto procedure testWorkflow(p : PdpMeeting) {
    process {
      while (p.report != "Dude") {
        writeReport(p) ; managerFillInForm(p)
      }
    }
  }*/
  
/*  auto procedure testWorkflow(p : PdpMeeting) {
    process {
      writeReport(p) ; 
      if (p.report != "Dude") {
        managerFillInForm(p)
      }
    }
  }*/


  procedure testWorkflow(p : PdpMeeting) {
    do {
      p.report := "Kanarie";
      p.persist();
    }
    process {
      (employeeFillInForm(p) xor managerFillInForm(p));
      repeat {
        writeReport(p);
        approveReport(p)
      } until finalizeReport(p)
    }
  }
  
  procedure employeeFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
    view {
      title{"Fill in employee form"}
      derive procedurePage from p for (employeePreparation)
    }
  }

  procedure managerFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    view {
      title{"Fill in manager form"}
      derive procedurePage from p for (managerPreparation)
    }
  }

  procedure writeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    view {
      title{"Write report"}
      derive procedurePage from p for (report)
    }
  }

  procedure finalizeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
  }

  procedure approveReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
  }
  
  auto procedure testDingesWorkflow(t : TestDinges) {
    process {
      proc1(t)
      ; if (t.child != null) {
          testDingesWorkflow(t.child)
        }
      ; proc2(t)
    }
  }
  
  procedure proc1(t : TestDinges) {
    view {
      title{"Fill proc1 for " text(t.name)}
      derive procedurePage from t for (proc1Text)
    }
  }
  
  procedure proc2(t : TestDinges) {
    view {
      title{"Fill proc1 for " text(t.name)}
      derive procedurePage from t for (proc2Text)
    }
  }
  
section pages

  globals {
    var test3: TestDinges;
  }

  define body() {}

  define page home() {
    main()
    define body() {
      var employee : User
      section {
        form {
          header{"Quick start PDP Meeting for employee"}
          action("Quick start", quickStart())
        
          action quickStart() {
            test3 := newTestDinges();
            test3.name := "Bovenste";
            test3.child := newTestDinges();
            test3.child.name := "Middelste";
            test3.child.child := newTestDinges();
            test3.child.child.name := "Onderste";
            test3.child.child.persist();
            test3.child.persist();
            test3.persist();
            test3.testDingesWorkflow.enable();
            
            var p : PdpMeeting := newPdpMeeting();
            p.employee := aUser;
            p.persist();
            p.testWorkflow.enable();
            
            return message("Tests gestart");
          }
        }
      }
      
      section {
        form {
          header{"Organize PDP Meeting"}
          "For: " input(employee)
          action("Organize", organize())

          action organize() {
/*            var p : PdpMeeting := newPdpMeeting();
            p.employee := employee;
            p.persist();
            p.testWorkflow.enable();
*/            // Test stuff
            return message("Done!");
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
    securityContext.principal = pdpMeeting.employee || securityContext.principal = pdpMeeting.employee.manager
  }
  rule page testDinges(t : testDinges) {
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
  define page testDinges(t : TestDinges) {
    derive procedureViewPage from t
  }

