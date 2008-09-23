module init

section global data

    var folder1 : Folder := Folder
    {
      name := "TU Delft"
      description := "the serious thing"
    };
    var folder2 : Folder := Folder
    {
      name := "Personal"
      description := "the important thing"
    };
    
    var n0 : Note := Note
    {
      name := "Finish Master Thesis"
      details := "Somewhere, this afternoon"
      finished := false
      urgent := true
      folder := folder1
    };
    var n1 : Note := Note
    {
      name := "Fetch a meal"
      details := "Before the shop closes"
      folder := folder2
    };
    var d2 : Note := Note
    {
      name := "Ask somebody to marry me"
      details := "Where to buy roses?"
      folder := folder2
      finished := true
    };
  