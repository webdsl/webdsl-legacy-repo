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
      title("Documents")
      list
      {
        for(d:Document)
        {
          navigateLinkListItemDoc(d)
        }
        div("loggedIn")
        {
          listitem { navigate(createDocument()){output("create new document")} }
        }
        for(o:Mission)
        {
          navigateLinkListItemMission(o)
        }
        div("loggedIn")
        {
          listitem { navigate(createMission()){output("create new Mission")} }
        }
      }

    }
  }

  define navigateLinkListItemMission(o:Mission)
  {
    listitem{ navigate(viewMission(o)) { output(o.title) } }
  }

  define navigateLinkListItemDoc(d:Document)
  {
    listitem{ navigate(viewDocument(d)) { output(d.title) } }
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
        input(d.title)
        input(d.text)
        input(d.classification)
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
        input(m.title)
        input(m.text)
        input(m.classification)
        action("save",save(m))
      }
    }
  }