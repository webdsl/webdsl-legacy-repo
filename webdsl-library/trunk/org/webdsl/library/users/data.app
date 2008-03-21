module org/webdsl/library/users/data

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
  
  globals {
    function makeUser(u:UserRegistration) : User {
      return User {
        username := u.username
        fullname := u.fullname
        email    := u.email
        homepage := u.homepage
        password := u.password
      };
    }
  }
