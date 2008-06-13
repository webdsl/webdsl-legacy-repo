application RBACextendedexample

imports accesscontrol
imports init
imports templates
imports data

description {
  RBAC example extended with parameterized roles
}

section RBAC pages

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
      table{viewRowsDocument(d)}
    }
  }
  
  define page viewUser(u:User)
  {
    main()
    define body()
    {
      table{viewRowsUser(u)}
    }
  }

  define page createNewDocument(p:Project)
  {
    var d:Document:=Document{project := p};
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

        action("save",save(d))
      }
    }
  }

  define page editDocument(d:Document)
  {

    action save(d:Document)
    {
      d.save();
      return viewDocument(d);
    }

    main()
    define body()
    {
      form
      {
        input(d.title)
        input(d.text)

        action("save",save(d))
      }

    }
  }
