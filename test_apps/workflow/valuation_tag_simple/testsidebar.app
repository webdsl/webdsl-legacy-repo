module testsidebar

section templates

  define testSidebarTemplate(d1 : Int, d2 : Int) {
    if (d1 == 1) {
      details1()
      navigate(testSidebar(0, d2)){"1 dichtklappen"}
    }
    if (d1 != 1) {
      navigate(testSidebar(1, d2)){"1 openklappen"}
    }
    if (d2 == 1) {
      details2()
      navigate(testSidebar(d1, 0)){"2 dichtklappen"}
    }
    if (d2 != 1) {
      navigate(testSidebar(d1, 1)){"2 openklappen"}
    }
  }
  
  define details1() {
    navigate(home()) {"blabla1"}
    navigate(home()) {"blabla2"}
    navigate(home()) {"blabla3"}
  }
  
  define details2() {
    navigate(home()) {"sdfsdf1"}
    navigate(home()) {"sdfsdf2"}
    navigate(home()) {"sdfsdf3"}    
  }

section pages

  define page testSidebar(d1 : Int, d2: Int) {
    main()
    define sidebar() {
      testSidebarTemplate(d1, d2)
    }
    define body() {
      text("Dit is echt vet. d1: ")
      text(d1)
      text(" d2: ")
      text(d2)
    }
  }