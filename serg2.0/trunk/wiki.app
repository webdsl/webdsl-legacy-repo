module app/wiki

sections domain

  entity Topic {
    title     :: String (name)
    body      :: Text
    authors   -> List<Person>
    created   :: Date
    modified  :: Date
    subtopics :: Map<String,Topic>
  }