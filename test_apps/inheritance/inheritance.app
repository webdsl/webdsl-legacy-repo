application com.example.inheritance

description {
  This is an automatically generated description
}

imports templates

section entities

entity Supper {
  isSupper :: String
}

entity Sub : Supper {

}

entity SupperContainer {
  it <> Supper
}

globals {
  var supperInstance : Supper :=
    Supper {
      isSupper := "yes"
    };
    
  var subInstance : Sub :=
    Sub {
      isSupper := "yes"
    };
    
  var container : SupperContainer :=
    SupperContainer {
      it := subInstance
    };
}

section pages

define page home() {
  main()
  define body() {
  	"Supper instance is:"
  	output(supperInstance)
//  	"Sub instance is:"
  	output(container)
  }
}
