module users/data

section users

  entity User {
    username    :: String (id,name)
    fullname    :: String
    email       :: Email (unique)
    password    :: Secret
    homepage    :: URL
    country     :: String
    affiliation :: String
    profile     :: WikiText
    confirmed   :: Bool (hidden)
  }
  
section registration

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
  
section groups
  
  entity UserGroup {
    name        :: String (id, name)
    description :: WikiText
    moderators  -> Set<User>
    members     -> Set<User>
    requested   -> Set<User>
  }
  
  extend entity User {
    groups -> Set<UserGroup> (inverse=UserGroup.members)
  }

  entity ACL {
    view     -> Set<UserGroup>
    edit     -> Set<UserGroup>
    moderate -> Set<UserGroup>
    admin    -> Set<UserGroup>
  }
  
  globals {
    var adminGroup     : UserGroup := UserGroup{name := "adminGroup"};
    var webCreateGroup : UserGroup := UserGroup{name := "webCreateGroup"};
    var allGroup       : UserGroup := UserGroup{name := "allGroup"};
  }