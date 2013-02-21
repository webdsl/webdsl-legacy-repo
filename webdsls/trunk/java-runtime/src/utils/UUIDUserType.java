package utils;

import java.io.IOException;
import java.io.Serializable ;
import java.sql.PreparedStatement ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Types ;
import java.util.UUID ;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.hibernate.type.SerializationException;

import org.hibernate.HibernateException ;
import org.hibernate.usertype.UserType ;
//http://www.bigsoft.co.uk/blog/index.php/2008/11/01/java-util-uuid-primary-keys-in-hibernate
//changed to work with varchar(16) field in db
public class UUIDUserType implements UserType, java.io.Serializable
{

	private static transient final String CAST_EXCEPTION_TEXT = " cannot be cast to a java.util.UUID." ;

	private static transient final Logger bindLog = LoggerFactory.getLogger( org.hibernate.type.descriptor.sql.BasicBinder.class );
	private static transient final Logger extractLog = LoggerFactory.getLogger( org.hibernate.type.descriptor.sql.BasicExtractor.class );
    private static transient final char[] digits = { '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' , 'a' , 'b' , 'c' , 'd' , 'e' , 'f' };

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable,
	 * java.lang.Object)
	 */
	public Object assemble (Serializable cached, Object owner) throws HibernateException
	{

		if(cached == null) return null;
		if (!String.class.isAssignableFrom (cached.getClass ()))
		{
			return null ;
		}

		return retrieveUUID((String) cached) ;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
	 */
	public Object deepCopy (Object value) throws HibernateException
	{
		if (value==null)
		{
			return null;
		}

		if (!UUID.class.isAssignableFrom (value.getClass ()))
		{
			throw new HibernateException (value.getClass ().toString () + CAST_EXCEPTION_TEXT) ;
		}

		UUID other = (UUID) value ;

		return UUID.fromString (other.toString ()) ;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
	 */
	public Serializable disassemble (Object value) throws HibernateException
	{
		if(value == null) return null;
		if (!UUID.class.isAssignableFrom (value.getClass ()))
		{
			throw new HibernateException (value.getClass ().toString () + CAST_EXCEPTION_TEXT) ;
		}
		return persistUUIDString((UUID)value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#equals(java.lang.Object,
	 * java.lang.Object)
	 */
	public boolean equals (Object x, Object y) throws HibernateException
	{
		if (x == y)
			return true ;

		if(x == null  ||  y == null)
			return false;

		if (!UUID.class.isAssignableFrom (x.getClass ()))
		{
			throw new HibernateException (x.getClass ().toString () + CAST_EXCEPTION_TEXT) ;
		}
		else if (!UUID.class.isAssignableFrom (y.getClass ()))
		{

			throw new HibernateException (y.getClass ().toString () + CAST_EXCEPTION_TEXT) ;
		}

		UUID a = (UUID) x ;
		UUID b = (UUID) y ;

		return a.equals (b) ;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
	 */
	public int hashCode (Object x) throws HibernateException
	{
		if (!UUID.class.isAssignableFrom (x.getClass ()))
		{
			throw new HibernateException (x.getClass ().toString () + CAST_EXCEPTION_TEXT) ;
		}

		return x.hashCode () ;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#isMutable()
	 */
	public boolean isMutable ()
	{

		return false ;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet,
	 * java.lang.String[], java.lang.Object)
	 */
	public Object nullSafeGet (ResultSet rs, String[] names, Object owner) throws HibernateException,
	SQLException
	{
		String value = rs.getString (names[0]) ;

		if (value == null)
		{
			if ( extractLog.isTraceEnabled() ) {
				extractLog.trace( "found [null] as column [{}]", names[0] );
			}
			return null ;
		}
		else
		{
			if ( extractLog.isTraceEnabled() ) {
				extractLog.trace( "found [{}] as column [{}]", retrieveUUID( value ), names[0] );
			}
			return retrieveUUID(value);
		}
	}
	
	public static String persistUUIDString(UUID uuid){
		long msb = uuid.getMostSignificantBits();
		long lsb = uuid.getLeastSignificantBits();
		char[] s = new char[32];
		for(int i = 15; i >= 0; i--) {
			s[i] = UUIDUserType.digits[(int)(msb & 0xF)];
			s[i + 16] = UUIDUserType.digits[(int)(lsb & 0xF)];
			msb >>>= 4;
			lsb >>>= 4;
		}
		return new String(s);
	}
	public static UUID retrieveUUID(String value){
		int i, shift;
		long msb = 0, lsb = 0, digit;
		for(i = 0, shift = 60; i < 16; i++, shift -= 4) {
			digit = Character.digit(value.charAt(i), 16);
			if(digit < 0) throw new NumberFormatException("For input string: \"" + value + "\"");
			msb |= digit << shift;
			digit = Character.digit(value.charAt(i + 16), 16);
			if(digit < 0) throw new NumberFormatException("For input string: \"" + value + "\"");
			lsb |= digit << shift;
		}
		return new UUID(msb, lsb);
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement,
	 * java.lang.Object, int)
	 */
	public void nullSafeSet (PreparedStatement st, Object value, int index)
	throws HibernateException, SQLException
	{
		// System.out.println("set start");
		if (value == null)
		{
			if ( bindLog.isTraceEnabled() ) {
				bindLog.trace( String.format("binding parameter [%d] as [VARCHAR] - <null>", index) );
			}

			st.setNull (index, theType) ;
			//System.out.println("set success null");
			return ;
		}

		if (!UUID.class.isAssignableFrom (value.getClass ()))
		{
			//	System.out.println("set fail");
			throw new HibernateException (value.getClass ().toString () + CAST_EXCEPTION_TEXT) ;
		}
		
		if ( bindLog.isTraceEnabled() ) {
			bindLog.trace( String.format("binding parameter [%d] as [VARCHAR] - %s", index, persistUUIDString((UUID) value)) );
		}

		st.setString (index, persistUUIDString((UUID) value));
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#replace(java.lang.Object,
	 * java.lang.Object, java.lang.Object)
	 */
	public Object replace (Object original, Object target, Object owner) throws HibernateException
	{

		if (!UUID.class.isAssignableFrom (original.getClass ()))
		{
			throw new HibernateException (original.getClass ().toString () + CAST_EXCEPTION_TEXT) ;
		}

		return UUID.fromString (original.toString ()) ;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#returnedClass()
	 */
	@SuppressWarnings("unchecked")
	public Class returnedClass ()
	{

		return UUID.class ;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#sqlTypes()
	 */
	public int[] sqlTypes ()
	{
		//System.out.println("sqlTypes: "+theType);
		return new int[] { theType } ;
	}

	public static final int theType =  org.hibernate.type.StringType.INSTANCE.sqlType(); // use db independent type of hibernate

}


