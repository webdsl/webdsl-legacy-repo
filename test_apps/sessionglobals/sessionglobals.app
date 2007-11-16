application com.example.sessionglobals

description {
  This is an automatically generated description
}

imports templates

section data model

  session session1 {
    name :: String
    bloobie :: Int
  }
  
  session session2 {
    name :: String
    bloobie :: Int
  }

  session session3 {
    name :: String
    bloobie :: Int
  }
  
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

section pages

define page home() {
  main()
  define body() {
    init {
      session1.name := "Piet Jan Hein";
      session1.bloobie := 10;
      session2.bloobie;
      session2.name := sayFoobar();
    }
    "Hello world!"
    output(f.name)
  }
}

define page home2() {
  output(f)
  output(f2.name)
}