// supertype Nonexistent is not a defined entity

application test

section datamodel

  entity User : Nonexistent {
    authoredPapers :: String
  }

  define page home() {
    "Hello world!"
  }
