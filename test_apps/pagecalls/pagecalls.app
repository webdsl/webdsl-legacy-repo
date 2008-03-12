application com.example.pagecalls

description {
  This is an automatically generated description
}

imports templates

section pages

globals {
  function selectPage() : URL {
    return message("Waddup");
  }
}

define page home() {
  main()
  define body() {
    "Hello world!"
    navigate(url("http://www.zefhemel.com")) { "My site" }
    navigate(message("Hoi")) { "A message from me" }
    form {
      action("Do something", doSomething())
      action doSomething() {
        return selectPage();
      }
      action("Do something2", doSomething2())
      action doSomething2() {
        return message("Bla");
      }
    }
  }
}

define page message(msg : String) {
  output(msg)
}
