application org.webdsl.pdp


description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports ac

section procedures

  auto procedure startWf(p : PdpMeeting) {
    /*process {
      (employeeFillInForm(p) |AND| managerFillInForm(p));
      writeReport(p)
    }*/
    done {
      p.employeeFillInForm.enable();
      p.managerFillInForm.enable();
    }
  }

  procedure employeeFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
    done {
      p.branchEnd1.enable(); 
    }
    view {
      title{"Fill in employee form"}
      derive procedurePage from p for (employeePreparation)
    }
  }

  procedure managerFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    done {
      p.branchEnd2.enable(); 
    }
    view {
      title{"Fill in manager form"}
      derive procedurePage from p for (managerPreparation)
    }
  }

  auto procedure branchEnd1(p : PdpMeeting) {
    do {
      if(p.syncAlmostReady) {
        p.employeeManagerSync.enable();
        p.syncAlmostReady := false;
      } else {
        p.syncAlmostReady := true;
      }
    }
  }

  auto procedure branchEnd2(p : PdpMeeting) {
    do {
      if(p.syncAlmostReady) {
        p.employeeManagerSync.enable();
        p.syncAlmostReady := false;
      } else {
        p.syncAlmostReady := true;
      }
    }
  }

  auto procedure employeeManagerSync(p : PdpMeeting) {
    done {
      p.writeReport.enable();
    }
  }

  procedure writeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    done {
      p.finalizeReport.enable();
      p.writeReport.enable();
    }
    view {
      title{"Write report"}
      derive procedurePage from p for (report)
    }
  }

  procedure finalizeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    done {
      p.approveReport.enable();
      p.writeReport.disable();
    }
  }

  procedure approveReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
  }

section pages

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
          var p : PdpMeeting := PdpMeeting{ };
          p.employee := employee;
          p.persist();
          p.startWf.enable();
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

/*
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
  */

