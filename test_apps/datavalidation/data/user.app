module user

  entity User {
    username :: String (name, id, validate(isUniquePersonId(this.username),"Username is taken"))
    password :: Secret (validate(password.length() >= 8, "Password needs to be at least 8 characters")
                       ,validate(password.containsLowerCase(), "Password must contain a lower-case character")
                       ,validate(password.containsUpperCase(), "Password must contain an upper-case character")
                       ,validate(password.containsDigit(), "Password must contain a digit"))
    person -> Person
  }
  
  define page editUser(u:User) {
    var p: Secret;
    form{
      formgroup("User")[labelWidth := globalSettings.labelWidth]{
        label("Username") { input(u.username) }
        label("New Password") { input(u.password) }
        label("Re-enter Password") { input(p){ validate(u.password == p, "Password does not match") } }
        block()[style := globalSettings.formButtonsStyle]{
          action("Save",save())
          navigatebutton(user(u),"Cancel")
        }
      }
    }
    action save(){
      u.save();
      return user(u);
    }
  }
  
  define page user(u:User) {
    outputMessage("User information successfully changed")
    formgroup("User")[labelWidth := globalSettings.labelWidth]{ 
      label("Username") { output(u.username) }
      label("Person") { output(u.person) }
      block()[style := globalSettings.formButtonsStyle]{
        navigate(editUser(u)){"edit"}
      }
    }    
  }
  
