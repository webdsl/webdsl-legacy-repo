application com.example.sessionglobals

description {
  This is an automatically generated description
}

imports templates

section data model

  entity Foo {
    name :: String
  }
  
  globals {
    var f : Foo := Foo { name := "FooBar" };
    var f2 : Foo := Foo { name := "Foocked" };
    
    function sayFoobar() : String {
      return "Foock";
    }
  }

  session something {
    i :: Int
  }

section pages

define page home() {
  main()
  define body() {
    init {
      something.i := 10;
    }
    "Hello world!"
    output(f.name)
    var foo : Foo := f;
    output(foo.name)
    for(f : Foo) {
      output(f.name)
    }
    form {
      action("Click me", doAction())
      action doAction() {
        something.i := 8;
      }
    }
  }
}

define page home2() {
  output(f)
  output(f2.name)
}
