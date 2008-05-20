module datamodel/conference

imports datamodel/user
imports datamodel/paper

section conference manager

  entity ConferenceManager {
    admin       -> User  
    conferences -> Set<Conference>
  }
  
section conference

  entity Conference {
    name             :: String (name)
    callforpapers    :: Text
    abstractDeadline :: DateTime
    paperDeadline    :: DateTime
    chairs           -> Set<User> 
    pc               -> Committee
    papers           -> Set<Paper>
  }

  extend entity User {
    registered      :: Bool
  }
  
section committees

  // e.g. "Program Committee", "Steering Committee"

  entity Committee {
    name        :: String (name) 
    description :: Text
    chairs      -> Set<User>
    members     -> Set<User>
    
    conference  -> Conference 
    
    // do we need a link to a conference?
    // is a committee reusable in other contexts?
    // instead of a link to the conference we could 
    // use a description of the purpose; i.e. can we
    // make the Committee workflow reusable (what is the
    // difference with a group?)
    
  }

  define showCommittee(c : Committee) {
    section {
      header{"Committee Members"}
      table {
        for (user : User in c.membersList) {
          row {
            output(user)
            // affilliation
            if(user in c.chairs) { "(chair)" }
          }
        }
      }
    }
  }