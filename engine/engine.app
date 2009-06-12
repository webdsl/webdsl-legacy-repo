application com.example.engine

description {
	This is an automatically generated description
}

imports data
imports ac
imports template

native function scheduleBuild(path : String, version : ApplicationVersion) : Bool;

section ui

define page home() {
  title { "WebDSL Engine" }
	main {
		"Please " navigate(signin()) { "sign in" }
	}
}

define page applications() {
  title { "Application listing" }
  main {
    section {
      header { output(securityContext.principal.name) "'s applications" }
      list {
        for(app : Application in securityContext.principal.applications) {
          listitem { output(app) }
        }
      }
    }
    form {
      header { "Add new application " }
      var app : Application := Application{}

      par { "Name: " input(app.name) }

      action("Add", add())

      action add() {
        app.owner := securityContext.principal;
        app.save();
        return applications();
      }
    }
  }
}

define page application(app : Application) {
  title { output(app.name) }
  main {
    section {
      header { "Versions of " output(app.name) }
      <table border="1">
        <tr>
          <th>"ID"</th>
          <th>"Download"</th>
        </tr>
        for(v : ApplicationVersion in app.versions) {
          <tr>
            <td>output(v.name)</td>
            <td>output(v.zip)</td>
          </tr>
        }
      </table>
    }

    form {
      header { "Upload new version" }
      var newVersion : ApplicationVersion := ApplicationVersion {}
      par{ "Zip file: " input(newVersion.zip) }
      action("Upload", upload())

      action upload() {
        newVersion.application := app;
        newVersion.save();
        scheduleBuild("/zef/engine/builds", newVersion);
        return application(app);
      }
    }
  }
}

define page signin() {
  title { "Sign in" }
  main {
    var username : String
    var password : Secret
    form {
      par { "Username: " input(username) }
      par { "Password: " input(password) }

      action("Login",signin())

      action signin() {
        log("Username: " + username);
        log("Password: " + password);
        if(authenticate(username, password)) {
          message("Welcome " + username);
          return applications();
        } 
        message("Wrong username, password combination.");
      }
    }
  }
}


define signinoffMenu() {
  if(!securityContext.loggedIn) {
    <li>navigate(signin()){"Sign in"}</li>
  } else {
    <li>"Whatever"</li>
    form{ 
      <li>actionLink("Sign off", signoffAction())</li>
      action signoffAction() {
        signoff();
      }
    }
  }
}

section authentication logic

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

section init

var admin : User := User { username := "admin", password := "qwe153" };
