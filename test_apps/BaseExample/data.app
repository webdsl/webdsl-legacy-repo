module data

section base example data

  entity Document
  {
    title :: String
    text :: String
    author -> User
  }

  entity User
  {
    name :: String
    password :: Secret
  }
