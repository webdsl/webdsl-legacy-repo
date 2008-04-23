module operations

section view page

  access control rules {
    rule view page pdpMeeting(p : PdpMeeting) {
      principal = p.employee || principal = p.employee.manager
    }
  }

  define view page pdpMeeting(p : PdpMeeting) {
    derive viewPage for p
  }

operations

  operation employeeFillInForm(p : PdpMeeting) {
    who { principal = p.employee }
    when { !status.employeeFilledIn }
    do {
      status.employeeFilledIn := true;
    }
    view {
      title{"Fill in employee form"}
      derive editPage for p (employeePreparation)
    }
  }

  operation managerFillInForm(p : PdpMeeting) {
    who("Manager" + p.employee.manager.name) { principal = p.employee.manager }
    when { !status.managerFillededIn }
    do {
      status.managerFilledIn := true;
    }
    view {
      title{"Fill in manager form"}
      derive editPage for p (managerPreparation)
    }
  }

  operation writeReport(p : PdpMeeting) {
    who { principal = p.employee.manager }
    when { status.employeeFilledInForm && status.managerFilledIn && !final}
    view {
      title{"Write report"}
      derive editPage for p (report)
    }
  }

  operation finalizeReport(p : PdpMeeting) {
    who { principal = p.employee.manager }
    when { status.employeeFilledInForm && status.managerFilledIn  && !status.final }
    do {
      status.final := true;
    }
  }

  operation approveReport(p : PdpMeeting) {
    who { principal = p.employee }
    when { status.employeeFilledInForm && status.managerFilledIn && !status.employeeApproved && status.final}
    do {
      status.employeeApproved := true;
    }
  }
