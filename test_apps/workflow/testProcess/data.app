module data

section data model

  entity User {
    username :: String
    name     :: String
    password :: Secret
    manager  -> User
  }

  entity PdpMeeting {
    employee -> User
    name :: String := this.employee.name + " PDP Form"
    employeePreparation :: Text
    managerPreparation :: Text
    report :: Text
  }
  
  entity TestDinges {
    name :: String
    comments :: String
    proc1Text :: String
    proc2Text :: String
    child -> TestDinges
  }

  

  globals {
    var aManager : User := User {
      username := "manager",
      name     := "Joe Manager",
      password := "secret"
    };
    var aUser : User := User {
      username := "user",
      name     := "Joe User",
      password := "secret",
      manager  := aManager
    };
    
  }
