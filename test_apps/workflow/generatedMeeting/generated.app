entity User {
  username  :: String
  password  :: Secret
  name      :: String
  manager   -> User
  employees -> Set<User> (inverse=User.manager)
}

entity ProgressMeeting {
  employee     -> User
  employeeView :: Text
  managerView  :: Text
  report       :: Text
  comment      :: Text

  meetings -> Set<ProgressMeeting>
}

entity ProcedureStatus {
  enabled :: Bool
  caller  -> ProcedureStatus
  returnstate :: Int

  function enable(caller : ProcedureStatus, returnstate : Int) {
    this.enabled := true;
    this.caller := caller;
    this.returnstate := returnstate;
    this.enabled();
  }

  function processed() {
    this.caller.next(this.returnstate);
  }
}

procedure meeting(p : ProgressMeeting) {
  process {
    employeeMeetings(p);
    beforeAndSplit(p);
    (employeeView(p); branch1end(p) |AND| managerView(p); branch2end(p));
    afterAndSplit(p);
    scheduleMeeting(p);
    repeat { 
      writeReport(p); 
      (approveReport(p) |XOR| commentReport(p))
    } until finalizeReport(p);
    endMeeting
  }
  enabled {
    this.next(0);
  }
}
entity MeetingStatus : ProcedureStatus {
  p -> ProgressMeeting

  function next(state : Int) {
    if(state == 0) { p.employeeMeetings.enable(this, 1); } // initial state
  }
}

extend entity MeetingStatus {
  extend function next(state : Int) {
    if(state == 1) { p.beforeAndSplit.enable(this, 2); }
  }
}

auto procedure beforeAndSplit(p) { }

extend entity MeetingStatus {
  extend function next(state : Int) {
    if(state == 2) { p.employeeView.enable(this, 3); p.managerView.enable(this, 4); }
  }
}

extend entity MeetingStatus {
  extend function next(state : Int) {
    if(state == 3) { p.branch1end.enable(this, 4); }
  }
}


auto procedure branch1end(p : ProgressMeeting) { }

extend entity MeetingStatus {
  extend function next(state : Int) {
    if(state == 4) {
      if(p.halfDone) {
        p.afterAndSplit.enable(this, 7);
	p.halfDone:=false;
      } else {
        p.halfDone := true; 
      }
    }
  }
}

extend entity MeetingStatus {
  extend function next(state : Int) {
    if(state == 5) { p.branch2end.enable(this, 6); }
  }
}

auto procedure branch2end(p : ProgressMeeting) { }

extend entity MeetingStatus {
  extend function next(state : Int) {
    if(state == 6) {
      if(p.halfDone) {
        p.afterAndSplit.enable(this, 7);
	p.halfDone:=false;
      } else {
        p.halfDone := true; 
      }
    }
  }
}


procedure employeeMeetings(p : ProgressMeeting) {
  who { principal = p.employee }
  do {
    for(u : User in p.employee.employeesList) {
      p.employees
       .add(ProgressMeeting{employee := u})
    }
  }
  process {
    andSplitPoint1(p);
    AND(m : ProgressMeeting in p.meetingsList) {
      meeting(m);
      andSyncPoint1(p)
    };
    andMergePoint1(p)
  }
  enabled {
    this.next(0);
  }
}
entity EmployeeMeetingsStatus : ProcedureStatus {
  p -> ProgressMeeting
}

extend entity EmployeeMeetingsStatus {
  extend function next(state : Int) {
    if(state == 1) { 
      for(m : ProgressMeeting in p.meetingsList) {
        m.meeting.enable(this, 2);
      }
    }
  }
}

extend entity EmployeeMeetingsStatus {
  extend function next(state : Int) {
    if(state == 2) { 
      p.andSyncPoint1.enable(this, 3);
    }
  }
}

auto procedure andSyncPoint1(p : ProgressMeeting) { }

extend entity EmployeeMeetingsStatus {
  extend function next(state : Int) {
    if(state == 3) {
      p.syncPointCounter := p.syncPointCounter + 1;
      if(p.syncPointCounter == p.meetingsList.length) {
        p.andMergePoint1.enable(this, 4);
      }
    }
  }
}
auto procedure andMergePoint1(p : ProgressMeeting) { }

extend entity EmployeeMeetingsStatus {
  extend function next(state : Int) {
    if(state == 4) {
      this.processed();
    }
  }
}

procedure employeeView(p : ProgressMeeting) {
  who { principal = p.employee }
  view {
    title{"Fill in employee view"}
    derive procedurePage from p 
       for (view(employee), employeeView)
  }
}
procedure managerView(p : ProgressMeeting) {
  who { principal = p.employee.manager }
  view {
    title{"Fill in manager view"}
    derive procedurePage from p 
       for (view(employee), managerView)
  }
}
procedure writeReport(p : ProgressMeeting) {
  who { principal = p.employee.manager }
  view {
    title{"Write report"}
    derive procedurePage from p 
       for (view(employee), view(employeeView), 
            view(managerView), report)
  }
}
procedure approveReport(p : ProgressMeeting) {
  who { principal = p.employee }
  view { derive procedurePage from p }            // ??
}
procedure commentReport(p : ProgressMeeting) {
  who { principal = p.employee }
  view {
    title{"Provide comments"}
    derive procedurePage from p 
       for (view(employee), view(report), 
            commments)
  }
}
procedure finalizeReport(p : ProgressMeeting) {
  who { principal = p.employee.manager }
  when { p.report != "" }
  view { derive procedurePage from p }            // ???
}
