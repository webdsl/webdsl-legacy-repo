application com.example.simple

description {
	This is an automatically generated description
}

section data

  entity Person {
    name     :: String
    age      :: Int
  }

section pages

define main() {
  header { "Zef's awesome page" }
  spacer
  body()
  spacer
  "(C) Zef Hemel"
}

define page home() {
  var p : Person := Person{};
  main()
  define body() {
    list {
      for(p : Person) {
        listitem { output(p.name) }
      }
    }
    form {
      "Name: " input(p.name)
      "Age: " input(p.age)

      action("Add", add())
      action add() {
        p.save();
        return home();
      }
    }
  }
}

