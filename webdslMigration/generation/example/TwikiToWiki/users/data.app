module users/data

section definition

  entity User {
    username    :: String (id,name)
    fullname    :: String
    email       :: Email (unique)
    password    :: Secret
    homepage    :: URL
    country     :: String
    affiliation :: String
    confirmed   :: Bool (hidden)
  }

  entity UserRegistration {
    username   :: String (notnull)
    fullname   :: String (notnull)
    email      :: Email  (notnull)
    homepage   :: URL
    password   :: Secret
    motivation :: WikiText
    confirmed  :: Bool (hidden)
  }
  
  extend entity UserRegistration {
    function makeUser() : User {
      return User {
        username := this.username
        fullname := this.fullname
        email    := this.email
        homepage := this.homepage
        password := this.password
      };
    }
  }
  
  entity UserGroup {
    groupname  :: String (id, name)
    fullname   :: String 
    moderators -> Set<User>
    members    -> Set<User>
  }