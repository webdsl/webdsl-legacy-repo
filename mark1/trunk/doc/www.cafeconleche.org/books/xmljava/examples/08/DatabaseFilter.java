import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.sql.*;
import java.io.*;


public class DatabaseFilter extends XMLFilterImpl {

  private Connection connection;
  
  // The string passed to the constructor must be a JDBC URL that
  // contains all necessary information for connecting to the
  // database such as host, port, username, password, and
  // database name. For example, 
  // jdbc:mysql://host:port]/dbname?user=username&password=pass
  // The driver should have been loaded before this method is
  // called
  public DatabaseFilter(String jdbcURL) throws SQLException {
    connection = DriverManager.getConnection(jdbcURL);
  }

  public void parse(InputSource in) throws SAXException, 
   IOException, UnsupportedOperationException {
    throw new UnsupportedOperationException(
     "Can't read a database from an InputStream or Reader"
    );
  }
  
  public void parse(String selectQuery) 
   throws SAXException, IOException {
    
    try {
      Statement statement = connection.createStatement();
      ResultSet data = statement.executeQuery(selectQuery);
      ResultSetMetaData metadata = data.getMetaData();
      int numFields = metadata.getColumnCount();
      
      Attributes emptyAttributes = new AttributesImpl();
      startElement("", "table", "table", emptyAttributes);
      
      while (data.next()) {
        startElement("", "record", "record", emptyAttributes);
        for (int field = 1; field <= numFields; field++) {
          AttributesImpl fieldAtts = new AttributesImpl();
          int type = metadata.getColumnType(field);
          String typeName = getSchemaType(type);
          fieldAtts.addAttribute(
           "http://www.w3.org/2001/XMLSchema-instance", "type", 
           "xsi:type", "NMTOKEN", typeName);
          String name = metadata.getColumnName(field);
          fieldAtts.addAttribute(
           "", "name", "name", "NMTOKEN", name);
          
          // Convert nulls to empty elements with xsi:nil="true"
          Object value = data.getObject(field);
          if (value == null) { // null value in database
            fieldAtts.addAttribute(
             "http://www.w3.org/2001/XMLSchema-instance", "nil", 
             "xsi:nil", "NMTOKEN", "true");
            startElement("", "field", "field", fieldAtts);
            endElement("", "field", "field");
          }
          else { // non-null value
            startElement("", "field", "field", fieldAtts);
            convertToXML(data, field, type);
            endElement("", "field", "field");
          }
        }
        endElement("", "record", "record");
      }
      endElement("", "table", "table");
      statement.close();
    }
    catch (SQLException e) {  // convert exception type
      throw new SAXException(e); 
    }
    
  }

  // I want the XML document to store values in the standard W3C
  // XML Schema Language forms. This requires certain conversions 
  // depending on the type of the data
  private void convertToXML(ResultSet data, int field, int type)
   throws SQLException, SAXException {

    switch (type) {
      case Types.BINARY: 
      case Types.VARBINARY: 
      case Types.LONGVARBINARY: 
        hexEncode(data.getBinaryStream(field));
        break;
      case Types.BLOB:
        Blob blob = data.getBlob(field);
        hexEncode(blob.getBinaryStream());
        break;
      case Types.CLOB: 
        Clob clob = data.getClob(field);
        Reader r = clob.getCharacterStream();
        char[] text = new char[1024];
        int numRead;
        try {
          while ((numRead = r.read(text, 0, 1024)) != -1) {
            escapeText(text, 0, numRead);
            characters(text, 0, numRead); 
          }
        }
        catch (IOException e) {
          throw new SAXException("Read from CLOB failed", e); 
        }
        break;
      case Types.ARRAY:
        Array array = data.getArray(field);
        writeArray(array);
        break;
      default: // All other types can be handled as strings
        Object o = data.getObject(field); 
        if (o == null) return;                
        String s = o.toString(); 
        char[] value = s.toCharArray();
        escapeText(value, 0, value.length);
        characters(value, 0, value.length);
    }     

  }
  
  private void hexEncode(InputStream in) 
   throws SQLException, SAXException {
    
    try {
      int octet;
      while ((octet = in.read()) != -1) {
        StringWriter out = new StringWriter(2);
        if (octet < 16) out.write('0');
        out.write(Integer.toHexString(octet));
        char[] text = out.toString().toCharArray();
        characters(text, 0, 2);
      }
    }
    catch (IOException e) {
      throw new SAXException(e);
    }
    
  }
 
  // String types may contain C0 control characters that are
  // not legal in XML. I convert these to the Unicode replacement
  // character 0xFFFD
  private void escapeText(char[] text, int start, int length) {
    for (int i = start; i < length; i++) {
        text[i] = escapeChar(text[i]);
    }
  }

  private char escapeChar(char c) {
    if (c >= 0x20) return c;
    else if (c == '\n') return c;
    else if (c == '\r') return c;
    else if (c == '\t') return c;
    return '\uFFFD';
  }
 
  private void writeArray(Array array) 
   throws SQLException, SAXException {
    
    ResultSet data = array.getResultSet();
    int type = array.getBaseType();
    String typeName = getSchemaType(type);

    while (data.next()) {
      AttributesImpl fieldAtts = new AttributesImpl();
      fieldAtts.addAttribute(
        "http://www.w3.org/2001/XMLSchema-instance", "type", 
        "xsi:type", "NMTOKEN", typeName);
      startElement("", "component", "component", fieldAtts);
      convertToXML(data, 2, type);
      endElement("", "component", "component");
    }
    
  }
  
  public static String getSchemaType(int type) {
   
    switch (type) {
      case Types.ARRAY:         return "array";
      case Types.BIGINT:        return "xsd:long";
      case Types.BINARY:        return "xsd:hexBinary";
      case Types.BIT:           return "xsd:boolean";
      case Types.BLOB:          return "xsd:hexBinary";
      case Types.CHAR:          return "xsd:string";
      case Types.CLOB:          return "xsd:string";
      case Types.DATE:          return "xsd:date";
      case Types.DECIMAL:       return "xsd:decimal";
      case Types.DOUBLE:        return "xsd:double";
      case Types.FLOAT:         return "xsd:decimal";
      case Types.INTEGER:       return "xsd:int";
      case Types.JAVA_OBJECT:   return "xsd:string";
      case Types.LONGVARBINARY: return "xsd:hexBinary";
      case Types.LONGVARCHAR:   return "xsd:string";
      case Types.NUMERIC:       return "xsd:decimal";
      case Types.REAL:          return "xsd:float";
      case Types.REF:           return "xsd:IDREF";
      case Types.SMALLINT:      return "xsd:short";
      case Types.STRUCT:        return "struct";
      case Types.TIME:          return "xsd:time";
      case Types.TIMESTAMP:     return "xsd:dateTime";
      case Types.TINYINT:       return "xsd:byte";
      case Types.VARBINARY:     return "xsd:hexBinary";
                                // most general type
      default:                  return "xsd:string"; 
    }
    
  }
  
  // Warn clients that this filter does not receive its events 
  // from another XML parser
  public void setParent(XMLReader parent) 
   throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
     "A DatabaseFilter reads from an underlying SQL database;"
     + " not an underlying XML parser"
    );
  }
  
}
