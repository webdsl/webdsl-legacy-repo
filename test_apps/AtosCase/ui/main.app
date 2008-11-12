module ui/main

section ui

define page berichtType(bt : BerichtType){
  derive viewPage from bt for (name)
}


define page inkomendBericht(ib : InkomendBericht){ 
  main()
  define body(){
    group("Inkomend Bericht"){
      derive viewRows from ib for(bestand,type,dossier)
    }
    if(ib.dossier == null) {
      form{
        action("Maak dossier", maakDossier())
      }
      action maakDossier() {
        ib.dossier := newDossier();
        ib.dossier.save();
        ib.dossier.beoordeel.enable();
        return dossier(ib.dossier);
      }
    }
  }
}
define page editInkomendBericht(ib : InkomendBericht){ 
  derive editPage from ib for(bestand,type)
}
define page nieuwInkomendBericht(){ 
  var ib : InkomendBericht := InkomendBericht{}; 
  derive createPage from ib for(bestand,type) 
}
define page inkomendeBerichten(){ 
  main()
  define body(){
    list{
      for(i : InkomendBericht){
        listitem{output(i)}
      }
    }
  }
}

define toonDossier(d : Dossier) {
  group("Dossier"){  
    derive viewRows from d for(inhoud)  
    derive viewRows from d.bericht for (type, bestand)
    if(d.beoordeeld) {
      derive viewRows from d for (eersteBeoordeling)
    }
  }
}

define page dossier(d : Dossier){ 
  main()
  define body() {
    toonDossier(d)
    dossierProceduresList(d)
  }
}

define page editDossier(d : Dossier){ 
  derive editPage from d for(bericht,inhoud) 
}

auto procedure beoordeel(d : Dossier) {
  process {
    repeat {
       aanvraagIsNietVolledig(d);
       bewerkAanvraag(d)
    } until aanvraagIsVolledig(d);
    beoordeelAanvraag(d)
//    beoordeelAanvraagNogmaals(d)
  }
  enabled {
    d.beoordeeld := false;
  }
  processed {
    d.beoordeeld := true;
  }
}

procedure aanvraagIsNietVolledig(d : Dossier) {

}

procedure aanvraagIsVolledig(d : Dossier) {

}

procedure bewerkAanvraag(d : Dossier) {
  view {
    main()
    define body() {
      form {
        group("Bewerk aanvraag") {
          derive editRows from d for (inhoud)
        }
        action("Bewaar", do())
      }
    }
  }

  do {
    d.save();
  }
}

procedure beoordeelAanvraag(d : Dossier) {
  view {
    main()
    define body() {
      toonDossier(d)
      form {
        group("Eerste beoordeling") {
          derive editRows from d for (eersteBeoordeling)
        }
        action("Lever beoordeling in", do())
      }
    }
  }
  do {
  
  }
}