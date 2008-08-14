application com.example.strategocalls

description {
  This is an automatically generated description
}

section pages

define page home() {
  main()
  define body() {
    "stratego calls testing"
     
    var input : SDFInput<WebDSL> ;
    var parsed : ATerm ;
    var moduleName : String;
    init{
      input := "application readme";
      parsed  := input.parse();
      moduleName  := parsed.get(0).toString();
    }
    "I just parsed" text(moduleName)
  }
}

entity exampleEntity {
  property :: String
}

