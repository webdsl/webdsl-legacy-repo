module for2

section for2
  
  globals
  {
        
    function for2():Bool
    {
      var result:Bool := false;
      
      // inner forall, outer with list
      result := true in [true in [doc0 = doc1 for(doc1:Document in select d from Document as d)] for(doc0:Document in docs.documents)];
    
      return result;
    }
    
  }
  
  define page for2()
  {
    output(for2())
  }