public class Notation {

  private String name;
  private String publicID;
  private String systemID;
  
  public Notation(String name, String publicID, 
   String systemID) {

    this.name = name;
    this.publicID = publicID;
    this.systemID = systemID;
    
  }
   
  public String getName() {
    return this.name;
  }
    
  public String getSystemID() {
    return this.systemID;
  }
    
  public String getPublicID() {
    return this.publicID;
  }
  
  public boolean equals(Object o) {
    
    if (o instanceof Notation) {
      Notation n = (Notation) o;
      // Well-formedness requires every notation to have  
      // at least a SYSTEM or a PUBLIC ID so both should not be
      // simultaneously null as long as the UnparsedCache built
      // this object
      if (publicID == null) {
        return name.equals(n.name) 
         && systemID.equals(n.systemID);
      }
      else if (systemID == null) {
        return name.equals(n.name) 
         && publicID.equals(n.publicID);
      }
      else {
        return name.equals(n.name) 
         && publicID.equals(n.publicID)
         && systemID.equals(n.systemID);
      }
    }
    return false;
    
  }
    
  public int hashCode() {
    
    if (publicID == null) {
      return name.hashCode() ^ systemID.hashCode();
    }
    else if (systemID == null) {
      return name.hashCode() ^ publicID.hashCode();
    }
    else {
      return name.hashCode() ^ publicID.hashCode() 
       ^ systemID.hashCode();
    }
    
  }

  public String toString() {
    
    StringBuffer result = new StringBuffer(name);
    if (publicID != null) {
      result.append(" PUBLIC "); 
      result.append(publicID);
      if (systemID != null) {
        result.append(" ");
        result.append(systemID);
      }
    }
    else {
      result.append(" SYSTEM "); 
      result.append(systemID);
    }
    return result.toString();
    
  }
    
}
