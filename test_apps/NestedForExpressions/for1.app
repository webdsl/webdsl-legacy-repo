module for1

section for1
  
  globals
  {
    

    function for1():Bool
    {
      var result:Bool := false;
      
      // both forall
      result := true in [true in [doc0 = doc1 for(doc1:Document in select d from Document as d)] for(doc0:Document in select d from Document as d)];
    
      return result;
    }
    
  }
  
  define page for1()
  {
    output(for1())
  }