module for3

section for3
  
  globals
  {
    
    function for3():Bool
    {
      var result:Bool := false;
      
      // outer forall, inner with list
      result := true in [true in [doc0 = doc1 for(doc1:Document in docs.documents)] for(doc0:Document in select d from Document as d)];
    
      return result;
    }    
    
    
  }
  
  define page for3()
  {
    output(for3())
  }