module data


section data model

  entity User {
    username :: String (name, id)
    password :: Secret
    enabled  :: Bool
    applications -> Set<Application> (inverse=Application.owner)
  }

  entity Application {
    name :: String (name, id)
    owner -> User
    versions -> Set<ApplicationVersion>
  }

  entity ApplicationVersion {
    application -> Application (inverse=Application.versions)
    log         :: Text
    zip         :: File
  }
