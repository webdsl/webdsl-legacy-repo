module initialize

section global vars

  globals {
    var user01: User := User {
      username := "zef"
      name := "Zef Hemel"
      password := "secret"
      email := "zef@zefhemel.com"
      isAdmin := true
      registered := true
    };
    var user02: User := User {
      username := "eelco"
      name := "Eelco Visser"
      password := "secret"
      email := "visser@acm.org"
      isAdmin := true
      registered := true
    };
    var user03: User := User {
      username := "ruben"
      name := "Ruben Verhaaf"
      password := "secret"
      email := "rverhaaf@gmail.com"
      isAdmin := true
      registered := true
    };
    var user04: User := User {
      username := "somebody"
      name := "Some Author"
      password := "secret"
      email := "bla@blueh.com"
      registered := true
    };
    var manager : ConferenceManager := ConferenceManager {
      admin := user02
    };
  }