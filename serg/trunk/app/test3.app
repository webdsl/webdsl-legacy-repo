application org.webdsl.test3

section domain. 

  Person {
    fullname :: String (name)
  }

section setup.

  define main() {
    sidebar()
    body()
  }
  
  define page home() {}
  