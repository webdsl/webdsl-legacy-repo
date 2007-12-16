module for4

section for4
  
  globals
  {
    function testequalityfunction(first:Document,second:Document):Bool
    {
      return first=second;
    }
    
    function for4():Bool
    {
      return true in [true in [testequalityfunction(doc0,doc1) for(doc1:Document in docs.documents)] for(doc0:Document in docs.documents)];
    }
    
  }
  
  define page for4()
  {
    output(for4())
  }