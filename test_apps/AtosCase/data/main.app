module data/main

section data model

enum BerichtType {
  fysiekBericht ("fysiek bericht"),
  elektronischBericht ("elektronisch bericht")
}

entity InkomendBericht{
  bestand :: Text
  type -> BerichtType
  dossier -> Dossier
}

entity Dossier {
  bericht           -> InkomendBericht (inverse = InkomendBericht.dossier)
  inhoud            :: Text
  eersteBeoordeling :: Text
  beoordeeld        :: Bool
}

/*
entity Aanvraag {
  Kennisgevingsnummer 
}*/

