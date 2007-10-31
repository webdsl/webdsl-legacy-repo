module app/publications

section publications

  entity Publication {
    title    :: String (name)
    authors  -> List<Person>
  }
