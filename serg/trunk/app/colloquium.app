module colloquium

description

  A colloquium is a series of presentations.
  
end

section domain.

  Colloquium {
    name        :: String
    talks       <> List<Presentation>
    contact     -> Person
    mailinglist :: Email
  }
  
  Presentation {
    title    :: String (name)
    speaker  -> Person
    date     :: Date
    time     :: Date
    end      :: Date
    Venue    :: String
    abstract :: Text
  }
  