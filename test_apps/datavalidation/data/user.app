module user

  entity User {
    username :: String (name, id, validate(isUniqueUser(this),"Username is taken"))
    password :: Secret (validate(password.length() >= 8, "Password needs to be at least 8 characters")
                       ,validate(password.containsLowerCase(), "Password must contain a lower-case character")
                       ,validate(password.containsUpperCase(), "Password must contain an upper-case character")
                       ,validate(password.containsDigit(), "Password must contain a digit"))
    email :: Email
  }

  define page editUserSmall(u:User) {
    var p: Secret;
    form{
      fieldset("User"){
        label("Username") { input(u.username) }
        label("Email") { input(u.email) }
        action("Save",save())
      }
    }
    action save(){
      u.save();
      message("User information successfully changed");
      return user(u);
    }
  }
  
  define page editUserBig(u:User) {
    var p: Secret;
    form{
      formgroup("User")[labelWidth := globalSettings.labelWidth]{
        label("Username") { input(u.username) }
        label("Email") { input(u.email) }
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
      message("User information successfully changed");
      return user(u);
    }
  }
  
  define page editUser(u:User) {
    var p: Secret;
    form{
      formgroup("User")[labelWidth := globalSettings.labelWidth]{
        label("Username") { input(u.username) }
        label("Email") { input(u.email) }
        label("New Password") { input(u.password) }
        block()[style := globalSettings.formButtonsStyle]{
          action("Save",save())
          navigatebutton(user(u),"Cancel")
        }
      }
    }
    action save(){
      u.save();
      message("User information successfully changed");
      return user(u);
    }
  }
  
  define page user(u:User) {
    formgroup("User")[labelWidth := globalSettings.labelWidth]{ 
      label("Username") { output(u.username) }
      label("Email") { output(u.email) }
      block()[style := globalSettings.formButtonsStyle]{
        navigate(editUser(u)){"edit"}
      }
    }    
  }
  
