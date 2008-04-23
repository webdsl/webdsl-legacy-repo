application org.webdsl.pdp

description {
  A workflow example for a personal development plan workflow
}

imports templates
imports data
imports ac

operations for PdpMeeting

  operation employeeFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
    when { !status.employeeFilledIn }
    do {
      status.employeeFilledIn := true;
    }
    view {
      title{"Fill in employee form"}
      main()
      define contextSidebar() {
        pdpMeetingOperations(p)
      }
      define body() {
        form {
          table {
            row { "Employee preparation" input(p.employeePreparation) }
          }
          action("Submit", do())
        }
      }
    }
  }

  operation managerFillInForm(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { !status.managerFilledIn }
    do {
      status.managerFilledIn := true;
    }
    view {
      title{"Fill in manager form"}
      main()
      define contextSidebar() {
        pdpMeetingOperations(p)
      }
      define body() {
        form {
          table {
            row { "Manager preparation" input(p.managerPreparation) }
          }
          action("Submit", do())
        }
      }
    }
  }

  operation writeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { status.employeeFilledIn && status.managerFilledIn && !status.final}
    view {
      title{"Write report"}
      main()
      define contextSidebar() {
        pdpMeetingOperations(p)
      }
      define body() {
        form {
          table {
            row { "Report" input(p.report) }
          }
          action("Submit", do())
        }
      }
    }
  }

  operation finalizeReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee.manager }
    when { status.employeeFilledIn && status.managerFilledIn  && !status.final }
    do {
      status.final := true;
    }
  }

  operation approveReport(p : PdpMeeting) {
    who { securityContext.principal = p.employee }
    when { status.employeeFilledIn && status.managerFilledIn && !status.employeeApproved && status.final}
    do {
      status.employeeApproved := true;
    }
  }

section pages

  define page home() {
    main()
    define body() {
      "Hello world!"
    }
  }

  access control rules {
    rules page pdpMeeting(pdpMeeting : PdpMeeting) {
      securityContext.principal = pdpMeeting.employee || securityContext.principal = pdpMeeting.employee.manager
    }
  }

  define page pdpMeeting(pdpMeeting : PdpMeeting) {
    title {"Pdp Meeting " output(pdpMeeting)}
    main()
    define contextSidebar() {
      pdpMeetingOperations(pdpMeeting)
    }
    define body() {
      header{"Pdp meeting " output(pdpMeeting)}
      table {
        row { "Employee:" output(pdpMeeting.employee) }
        row { "Employee preparation:" output(pdpMeeting.employeePreparation) }
        row { "Manager preparation:" output(pdpMeeting.managerPreparation) }
        row { "Report:" output(pdpMeeting.report) }
      }
    }
  }

