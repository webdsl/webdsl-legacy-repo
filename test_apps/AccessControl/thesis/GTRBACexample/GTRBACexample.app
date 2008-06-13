application GTRBACexample

imports accesscontrol
imports init
imports templates
imports data

description {
  GTRBAC example

  Administrator may change roles, and enable/disable them

  Edit permission may edit and view documents

  View permission may view documents
  
  Manage user-role and role-permission assignments

  explicit role activation
  
  most of GTRBAC elements need time operations and cannot be expressed in webdsl yet
}

section GTRBAC pages

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
