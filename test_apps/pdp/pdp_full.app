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
      form {
        table {
          row { "Whatever" }
        }
      }
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

  define page pdpMeetingTasks() {
    title{"Pdp Meeting Tasks"}
    main()
    define body() {
      list {
        for(pdpMeeting : PdpMeeting) {
          listitem {
            output(pdpMeeting)
            pdpMeetingOperations(pdpMeeting)
          }
        }
      }
    }
  }

  define page pdpMeetingStatuses() {
    title{"Pdp Meeting Statuses"}
    main()
    define body() {
      list {
        for(pdpMeeting : PdpMeeting) {
          listitem {
            output(pdpMeeting)
            list {
              if(!pdpMeeting.employeeFilledIn) {
                listitem {
                  navigate(employeeFillInForm(pdpMeeting)) { "Employee fill in form" } 
                }
              }
              if(!pdpMeeting.managerFilledIn) {
                listitem {
                  navigate(managerFillInForm(pdpMeeting)) { "Manager fill in form" } 
                }
              }
              if(!pdpMeeting.employeeFilledIn && pdpMeeting.managerFilledIn && !pdpMeeting.final) {
                listitem {
                  navigate(writeReport(pdpMeeting)) { "Write report" }
                }
              }
              if(pdpMeeting.employeeFilledIn && pdpMeeting.managerFilledIn  && !pdpMeeting.final) {
                listitem {
                  navigate(finalizeReport(pdpMeeting)) { "Finalize report" }
                }
              }
              if(pdpMeeting.employeeFilledIn && pdpMeeting.managerFilledIn && !pdpMeeting.employeeApproved && pdpMeeting.final) {
                listitem {
                  navigate(approveReport(pdpMeeting)) { "Approve report" }
                }
              }
            }
          }
        }
      }
    }
  }

section operation pages

  access control rules { 
    rules page employeeFillInForm(pdpMeeting : PdpMeeting) {
      securityContext.principal = pdpMeeting.employee && !pdpMeeting.employeeFilledIn 
    }
  }
  define page employeeFillInForm(pdpMeeting : PdpMeeting) {
    action doaction() {
      pdpMeeting.employeeFilledIn := true;
      return pdpMeeting(pdpMeeting);
    }
    title{"Fill in employee form"}
    main()
    define contextSidebar() {
      pdpMeetingOperations(pdpMeeting)
    }
    define body() {
      form {
        table {
          row { "Employee preparation" input(pdpMeeting.employeePreparation) }
        }
        action("Submit", doaction())
      }
    }
  }

  access control rules { 
    rules page managerFillInForm(pdpMeeting : PdpMeeting) {
      securityContext.principal = pdpMeeting.employee.manager && !pdpMeeting.managerFilledIn
    }
  }
  define page managerFillInForm(pdpMeeting : PdpMeeting) {
    action doaction() {
      pdpMeeting.managerFilledIn := true;
      return pdpMeeting(pdpMeeting);
    }
    title{"Fill in manager form"}
    main()
    define contextSidebar() {
      pdpMeetingOperations(pdpMeeting)
    }
    define body() {
      form {
        table {
          row { "Manager preparation" input(pdpMeeting.managerPreparation) }
        }
        action("Submit", doaction())
      }
    }
  }

  access control rules { 
    rules page writeReport(pdpMeeting : PdpMeeting) {
      securityContext.principal = pdpMeeting.employee.manager && pdpMeeting.employeeFilledIn && pdpMeeting.managerFilledIn && !pdpMeeting.final
    }
  }
  define page writeReport(pdpMeeting : PdpMeeting) {
    action doaction() {
      return pdpMeeting(pdpMeeting);
    }
    title{"Write report"}
    main()
    define contextSidebar() {
      pdpMeetingOperations(pdpMeeting)
    }
    define body() {
      form {
        table {
          row { "Report" input(pdpMeeting.report) }
        }
        action("Submit", doaction())
      }
    }
  }

  access control rules { 
    rules page writeReport(pdpMeeting : PdpMeeting) {
      securityContext.principal = pdpMeeting.employee.manager && pdpMeeting.employeeFilledIn && pdpMeeting.managerFilledIn  && !pdpMeeting.final 
    }
  }
  define page finalizeReport(pdpMeeting : PdpMeeting) {
    init {
      pdpMeeting.final := true;
      goto pdpMeeting(pdpMeeting);
    }
  }

  access control rules { 
    rules page approveReport(pdpMeeting : PdpMeeting) {
      securityContext.principal = pdpMeeting.employee && pdpMeeting.employeeFilledIn && pdpMeeting.managerFilledIn && !pdpMeeting.employeeApproved && pdpMeeting.final
    }
  }
  define page approveReport(pdpMeeting : PdpMeeting) {
    init {
      pdpMeeting.employeeApproved := true;
      goto pdpMeeting(pdpMeeting);
    }
  }

section template definitions

  define pdpMeetingOperations(pdpMeeting : PdpMeeting) {
    list {
      if(securityContext.principal = pdpMeeting.employee && !pdpMeeting.employeeFilledIn) {
        listitem {
          navigate(employeeFillInForm(pdpMeeting)) { "Employee fill in form" } 
        }
      }
      if(securityContext.principal = pdpMeeting.employee.manager && !pdpMeeting.managerFilledIn) {
        listitem {
          navigate(managerFillInForm(pdpMeeting)) { "Manager fill in form" } 
        }
      }
      if(securityContext.principal = pdpMeeting.employee.manager && !pdpMeeting.employeeFilledIn && pdpMeeting.managerFilledIn && !pdpMeeting.final) {
        listitem {
          navigate(writeReport(pdpMeeting)) { "Write report" }
        }
      }
      if(securityContext.principal = pdpMeeting.employee.manager && pdpMeeting.employeeFilledIn && pdpMeeting.managerFilledIn  && !pdpMeeting.final) {
        listitem {
          navigate(finalizeReport(pdpMeeting)) { "Finalize report" }
        }
      }
      if(securityContext.principal = pdpMeeting.employee && pdpMeeting.employeeFilledIn && pdpMeeting.managerFilledIn && !pdpMeeting.employeeApproved && pdpMeeting.final) {
        listitem {
          navigate(approveReport(pdpMeeting)) { "Approve report" }
        }
      }
    }
  }
