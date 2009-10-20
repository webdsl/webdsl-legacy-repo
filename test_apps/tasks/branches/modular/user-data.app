module user-data

entity User {
  username   :: String (id, name, validate(isUniqueUser(this), "Username is taken"))
  password   :: Secret
}

function principal() : User {
  return securityContext.principal;
}

function authenticate(username : String, password : Secret) : Bool {
  var user : User := getUniqueUser(username);
  if(user != null) {
    if(user.password.check(password)) {
      securityContext.principal := user;
      securityContext.loggedIn := true;
      return true;
    }
  }
  securityContext.loggedIn := false;
  return false;
}

function signoff() {
  securityContext.loggedIn := false;
  securityContext.principal := null;
}
