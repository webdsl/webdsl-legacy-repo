package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import javax.faces.convert.ConverterException;
import org.apache.commons.codec.binary.Hex;

public final class Encoders {
    
    public static String encodeTemplateId(String name,/*String argsrep,*/ String templateContext)
    {
        //System.out.println(name+" "+templateContext);
         
        String d = name + templateContext;
       
        // get a Message Digest from the threadlocal page object, that way, only one MD per thread is used (MD is not thread-safe)
        MessageDigest md = utils.ThreadLocalPage.get().getMessageDigest();

        // Create the message
        // Update the message digest with some more bytes
        // This can be performed multiple times before creating the hash
        md.reset();
        md.update(d.getBytes());
        //md.update(argsrep.getBytes()); // removed arguments from deterministic id, arguments can change between phases

        // Create the digest from the message
        String s = new String(Hex.encodeHex(md.digest()));
        
        //System.out.println("Result: "+s);
        
        return s;
    }
}
