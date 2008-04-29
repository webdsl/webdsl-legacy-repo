module datamodel/accesscontrol

section data model access control

  entity User {
    username        :: String (name)
    name            :: String
    email           :: Email (id)
    password        :: Secret
    isAdmin         :: Bool
  }

  define page editUser(us : User) {
    main()
    body() {
      header{"Edit " output(us.name)}
      form {
        table {
          derive editRows from us
          row {
            action("Save", save())
            action save() {
              us.password = us.password.digest();
              us.save();
            }
          }
        }
      }
    }
  }