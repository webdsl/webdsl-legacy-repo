module forinif

section forinif

  define page forinif()
  {
    if(true in [true in [doc0 = doc1 for(doc1:Document in docs.documents)] for(doc0:Document in docs.documents)])
    {
      "if check"
    }
  }