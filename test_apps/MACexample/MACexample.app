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

  define page home()
  {
    main()
    define body()
    {
      menubar("vertical")
      {
        themenu()
      }
    }
  }

  define page viewDocument(d:Document)
  {
    main()
    define body()
    {
      output(d.title)
      output(d.text)
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