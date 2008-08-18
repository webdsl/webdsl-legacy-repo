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

    syncAlmostReady :: Bool
  }

  /*
  status PdpMeeting {
    employeeFilledIn :: Bool
    managerFilledIn  :: Bool
    final            :: Bool
    employeeApproved :: Bool
  }
  */

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
