module for0

section for0
  
  globals
  {
    function for0():Bool
    {
      var result:Bool := false;
      
      // both for with list
      result := true in [true in [doc0 = doc1 for(doc1:Document in docs.documents)] for(doc0:Document in docs.documents)];
    
      return result;
    }
    
  }
  
  define page for0()
  {
    output(for0())
  }