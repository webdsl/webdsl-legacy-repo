module logic

section functions

  function bronFormaatNaarRegistratie() {
    for(b : BronFormaat) {
      if(!b.verwerkt) {
        var registratie : Registratie := Registratie { };
        registratie.docCode := b.doccode;
        registratie.ontvangstDatum := b.duedate;
        registratie.bedrag := b.valuedoc;
        registratie.omschrijving := b.comm;
        registratie.afdeling := b.usrref1;
        registratie.save();
        b.verwerkt := true;
        b.save();
      }
    }
  }
