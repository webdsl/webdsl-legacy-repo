module init

section test setup for colloquium application

  globals {

    var eelco : Person := Person {
      firstname    := "Eelco"
      lastname     := "Visser"
      affilliation := "Delft University of Technology"
      email        := "e.visser@tudelft.nl"
      homepage     := "http://www.eelcovisser.net"
    };

    var danny : Person := Person {
      firstname    := "Danny"
      lastname     := "Groenewegen"
      affilliation := "Delft University of Technology"
      email        := "dgroenewegen@gmail.com"
    };

    var martin : Person := Person {
      firstname    := "Martin"
      lastname     := "Bravenboer"
      affilliation := "Delft University of Technology"
      email        := "martin.bravenboer@gmail.com"
    };

    var dsleng : Presentation := Presentation {
      speaker  := eelco
      title    := "Domain-Specific Language Engineering: Part I"
      abstract := "In this presentation I give a tutorial on the construction of DSLs."
      //date     := "2007/05/22"
      //time     := "10:30"
      duration := "2:00"
      room     := hb9130
      status   := confirmed
    };
   
    var ac : Presentation := Presentation {
      speaker  := danny
      title    := "A DSL for Access Control"
      abstract := "Talk about an extension of WebDSL with declarative access control rules"
      //date     := "2007/11/22"
      //time     := "11:00"
      duration := "0:45"
      room     := hb9130
      status   := tentative
    };
   
    var syntax : Presentation := Presentation {
      speaker  := martin
      title    := "Syntax Embedding"
      abstract := "concrete syntax and stuff"
      //date     := "2007/11/01"
      //time     := "10:30"
      duration := "2:00"
      room     := hb9130
      status   := confirmed
    };
   
    var modse : Colloquium := Colloquium {
      acronym       := "MoDSE"
      fullname      := "Model-Driven Software Evolution Colloquium"
      description   := "The MoDSE colloquium is a series of in depth lectures in the context of the NWO JAQUARD project on Model Driven Software Evolution. Lectures start at 10:30 and are scheduled to last until 12:30 (with a break somewhere in the middle). This should allow time for an in depth exploration of the topic as well as discussion. You are welcome to join us for lunch afterwards in the canteen."
      presentations := {dsleng,syntax,ac}
      moderator    := eelco
    };

    //dsleng.colloquium := modse;

  }
