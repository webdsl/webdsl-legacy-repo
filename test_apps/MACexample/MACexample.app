application MACexample

imports accesscontrol
imports init
imports templates
imports data

description {
  MAC example

  BLP Model read down, write up
  enforces confidentiality
  Document

  Biba Model read up, write down
  enforces integrity
  Mission
}

section MAC pages

  define page home(){
    //quick fix (bug with Int parameter):
    var do0:Document := Document{ classification := 0 };
    var do1:Document := Document{ classification := 1 };
    var do2:Document := Document{ classification := 2 };
    var do3:Document := Document{ classification := 3 };
  
    main()
    define body(){
      "Viewing: "
      for(d:Document){
        section{ navigate(viewDocument(d)){ output(d.title) } }
      }
      "Creating: "
      //bugged
      //for(classification:Int in [0,1,2,3])
      //{
      //  section{ navigate(createClassificationDocument(classification)){ "Create Document with classification" output(classification) } }
      //}
      section{ navigate(createClassificationDocument(do0)){ "Create Document with classification" output(0) } }
      section{ navigate(createClassificationDocument(do1)){ "Create Document with classification" output(1) } }
      section{ navigate(createClassificationDocument(do2)){ "Create Document with classification" output(2) } }
      section{ navigate(createClassificationDocument(do3)){ "Create Document with classification" output(3) } }
    }
  }

  define page viewDocument(d:Document){
    main()
    define body(){
      output(d.title)
      output(d.text)
    }
  }


  //define page createClassificationDocument(cl:Int)
  define page createClassificationDocument(d:Document)
  {
    //var d:Document := Document{ classification := cl };

    action save(d:Document){
      d.save();
      return home();
    }

    main()
    define body(){
      form{
        table{
          row{ "Title:" input(d.title) }
          row{ "Text:" input(d.text) }
        }
        action("save",save(d))
      }
    }
  }

  define page createDocument()
  {
    var d:Document:=Document{};
    action save(d:Document)
    {
      d.save();
      return home();
    }

    main()
    define body()
    {
      form
      {
        editRowsDocument(d)
        action("save",save(d))
      }
    }
  }

  define page viewMission(m:Mission)
  {
    main()
    define body()
    {
      output(m.title)
      output(m.text)
    }
  }

  define page createMission()
  {
    var m:Mission:=Mission{};
    action save(m:Mission)
    {
      m.save();
      return home();
    }

    main()
    define body()
    {
      form
      {
        editRowsMission(m)
        action("save",save(m))
      }
    }
  }