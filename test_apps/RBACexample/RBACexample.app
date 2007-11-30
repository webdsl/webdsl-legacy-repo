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
      title("Documents")
      list
      {
        for(d:Document)
        {
          output(d.title)
          navigateLinkListItemDoc(d)
          navigateLinkListItemEditDoc(d)
        }
      }
      list
      {
        for(u:User)
        {
          output(u.name)
          navigateLinkListItemUser(u)
        }
      }
      div("canEdit")
      {
        list
        {
          listitem { navigate(createDocument()){output("create new document")} }
        }
      }
    }
  }

  define navigateLinkListItemUser(u:User)
  {
    listitem{ navigate(editUserRoles(u)) { " edit roles" } }
  }

  define navigateLinkListItemDoc(d:Document)
  {
    listitem{ navigate(viewDocument(d)) { " view" } }
  }

  define navigateLinkListItemEditDoc(d:Document)
  {
    listitem{ navigate(editDocument(d)) { " edit" } }
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
