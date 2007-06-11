module issues

description

  Issue descriptions and assignments to people.
  
end

section domain.

  Issue {
    title       :: String (name)
    description :: Text
    due         :: Date
    priority    :: Int
    issues      <> Set<Issue>
    assigned    -> Set<Person>
    status      :: String // enum of Open, Closed, ...
//  type        :: String // corresponds to subclasses
  }
  
  Project : Issue {}
  Bug     : Issue {}
  Task    : Issue {}
  
section pages.

