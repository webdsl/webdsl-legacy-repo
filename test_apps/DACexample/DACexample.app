application DACexample

imports accesscontrol
imports init
imports templates
imports data

description {
  DAC example

  subjects: users
  objects: documents
  rights: held within the objects

  no change of owner
  one level granting
}

section DAC pages

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
          navigateLinkListItemGrants(d)
          navigateLinkListItemGrantingRights(d)
        }
        div("loggedIn")
        {
          listitem { navigate(createDocument()){output("create new document")} }
        }
      }

    }
  }

  define navigateLinkListItemDoc(d:Document)
  {
    listitem{ navigate(viewDocument(d)) { " view" } }
  }

  define navigateLinkListItemEditDoc(d:Document)
  {
    listitem{ navigate(editDocument(d)) { " edit" } }
  }

  define navigateLinkListItemGrants(d:Document)
  {
    listitem{ navigate(editGrants(d)) { " edit view/edit access" } }
  }

  define navigateLinkListItemGrantingRights(d:Document)
  {
    listitem{ navigate(editGrantingRights(d)) { " edit granting rights" } }
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
      d.owner:=securityContext.principal;
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

  define page editGrants(d:Document)
  {
    main()
    define body()
    {
      form
      {
        "ViewAccess: " input(d.viewAccess)
        "EditAccess: " input(d.editAccess)
        action("save",saveGrants(d))

        action saveGrants(d:Document)
        {
          d.save();
          return viewDocument(d);
        }
      }
    }
  }

  define page editGrantingRights(d:Document)
  {
    main()
    define body()
    {
      form
      {
        "GrantingRights: " input(d.grantingRights)

        action("save",saveGranting(d))

        action saveGranting(d:Document)
        {
          d.save();
          return viewDocument(d);
        }
      }
    }
  }