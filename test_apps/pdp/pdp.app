application org.webdsl.pdp


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports ac

section data

section procedures

  auto procedure startWf(p : PdpMeeting) {
    do {
      p.employeeFillInForm.enable();
    }
  }
  /*  process {
      (employeeFillInForm(p) |AND| managerFillInForm(p));
      writeReport(p)
    }*/

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

  define page home() {
    main()
    define body() {
      var employee : User
      form {
        header{"Organize PDP Meeting"}
        "For: " input(employee)
        action("Organize", organize())

        action organize() {
          var p : PdpMeeting := PdpMeeting{ };
          p.employee := employee;
          p.persist();
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

  define pdpMeetingOperations(p : PdpMeeting) {
    "haha!"
    pdpMeetingOperationsList(p)
  }

  define page pdpMeeting(pdpMeeting : PdpMeeting) {
    title {"Pdp Meeting " output(pdpMeeting)}
    main()
    define contextSidebar() {
      pdpMeetingprocedures(pdpMeeting)
    }
    define body() {
      header{"Pdp meeting " output(pdpMeeting)}
      table {
        derive viewRows from pdpMeeting
      }
    }
  }

