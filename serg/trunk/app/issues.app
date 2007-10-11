module issues

description

  Issue descriptions and assignments to people.
  
end

section domain.

  entity Issue {
    title       :: String (name)
    description :: Text
    due         :: Date
    priority    :: Int
    issues      -> Set<Issue>
    assigned    -> Set<Person>
    status      :: String // enum of Open, Closed, ...
//  type        :: String // corresponds to subclasses
  }
  
  entity Project : Issue {}
  entity Bug     : Issue {}
  entity Task    : Issue {}
  
section pages.

