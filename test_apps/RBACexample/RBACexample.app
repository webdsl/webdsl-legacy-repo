application RBACexample

imports accesscontrol
imports init
imports templates
imports data

description {
  RBAC example

  Administrator may change roles

  Editor may edit and view documents

  Viewer may view documents

  explicit role activation
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

  define page editUserRoles(u:User)
  {

    action save(u:User)
    {
      u.save();
      return home();
    }

    main()
    define body()
    {
      form
      {
        input(u.roles)

        action("save",save(u))
      }

    }
  }
