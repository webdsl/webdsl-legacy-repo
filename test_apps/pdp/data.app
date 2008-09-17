module data

section data model

  entity User {
    username :: String
    name     :: String
    password :: Secret
    manager  -> User
    employees-> Set<User>
  }

  entity Meeting {
    employee            -> User
    employeePreparation :: Text
    managerPreparation  :: Text
    approved            :: Bool
    comments            :: Text
    report              :: Text
    name :: String := this.employee.name + " Meeting"
  }

  var aManager : User := User {
    username := "manager",
    name     := "Hank",
    password := "secret"
  };
  var aUser : User := User {
    username := "user",
    name     := "Sue",
    password := "secret",
    manager  := aManager
  };
  var anotherUser : User := User {
    username := "user2",
    name     := "Jake",
    password := "secret",
    manager  := aManager
  };
