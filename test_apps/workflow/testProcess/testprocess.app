application org.webdsl.testProcess


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports ac

section procedures

  auto procedure testWorkflow(p : PdpMeeting) {
    process {
      (employeeFillInForm(p) |AND| managerFillInForm(p));
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
  
section pages

  define body() {}

  define page home() {
    main()
    define body() {
      var employee : User
      navigatebutton(signin(), "Sign in!")
      form {
        header{"Organize PDP Meeting"}
        "For: " input(employee)
        action("Organize", organize())

        action organize() {
          var p : PdpMeeting := newPdpMeeting();
          p.employee := employee;
          p.persist();
          p.testWorkflow.enable();
          // Test stuff
          return message("Done!");
        }
      }
    }
  }

access control rules 
  rule page pdpMeeting(pdpMeeting : PdpMeeting) {
    securityContext.principal = pdpMeeting.employee || securityContext.principal = pdpMeeting.employee.manager
  }
    
section pages

  // patch since no auto generation of view pages
  define page pdpMeeting(p : PdpMeeting) {
    derive procedureViewPage from p
  }
  define page user(u : User) {
    derive viewPage from u
  }

