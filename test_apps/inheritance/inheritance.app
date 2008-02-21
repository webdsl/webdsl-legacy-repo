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
  	"We have a container:" output(container)
  	
  	"It contains:" output(container.it)
  	
  	"And this is of type:"
  	
  	if(container.it is a Supper) { " Supper " }
  	if(container.it is a Sub)    { " Sub " }
  	if(subInstance is a Sub)    { " - sub is Sub " }
  }
}
