module user

  entity User {
    username :: String (name, id, validate(isUniqueUser(this),"Username is taken"))
    password :: Secret (validate(password.length() >= 8, "Password needs to be at least 8 characters")
                       ,validate(password.containsLowerCase(), "Password must contain a lower-case character")
                       ,validate(password.containsUpperCase(), "Password must contain an upper-case character")
                       ,validate(password.containsDigit(), "Password must contain a digit"))
    email :: Email
    employmentDate :: Date
    birthdate :: Date (validate(employmentDate.after(birthdate), username + " must have been born before being employed"))
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
  
  define page editUserBig(u : User) {
    var p: Secret;
    form{
      fieldset("User"){
        label("Username") { input(u.username) }
        label("Email") { input(u.email) }
        label("New Password") { input(u.password) }
        label("Re-enter Password") { input(p){ validate(u.password == p, "Password does not match") } }
        action("Save",save())
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
      fieldset("User"){
        label("Username") { input(u.username) }
        label("Email") { input(u.email) }
        label("New Password") { input(u.password) }
        action("Save",save())
      }
    }
    action save(){
      u.save();
      message("User information successfully changed");
      return user(u);
    }
  }
  
  define page user(u:User) {
    fieldset("User"){ 
      label("Username") { wrapProperty{ output(u.username) } }
      label("Email") {    wrapProperty{ output(u.email) } }
    }   
    break 
    navigate(editUser(u)){"edit"}
  }
  
  define wrapProperty(){
    <div class="outputproperty"> 
    elements
    </div>
  }
  define wrapListProperty(){
    <div class="outputlistproperty"> 
    elements
    </div>
  }
  
  define page editUserBirthdate(u:User) { 
    form{
      fieldset("User " + u.name){ 
        label("Birthdate") { input(u.birthdate){ validate(u.birthdate.before(today()),"Date must be before today") } }
        action("save",save())
      }
    }
    action save() { 
      checkUser(u);
      message(u.name + "'s birthdate successfully changed"); 
      return userBirthdate(u); 
    } 
  }
  
  function checkUser(u:User) {
    validate(u.birthdate.before(Date("01/01/2000")),"User is too young, birthdate must be before 01/01/2000");
  }
  
  define page userBirthdate(u:User) {
    fieldset("User"){ 
      label("Username") { wrapProperty{ output(u.username) } }
      //label("Email") {    wrapProperty{ output(u.email) } }
      label("Birthdate") {wrapProperty{ output(u.birthdate) } }
    }   
    break 
    navigate(editUserBirthdate(u)){"edit"}
  }
  