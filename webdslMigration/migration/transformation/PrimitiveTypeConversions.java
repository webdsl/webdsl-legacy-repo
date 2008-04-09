package transformation;

import java.lang.reflect.Method;

public class PrimitiveTypeConversions {
	
	/**
	 * Will select the method with the given name from this class and return it. All returned methods 
	 * require an input parameter of type Object and result in an Object (use casts depending on the given 
	 * name).
	 * @param name Name of the conversion
	 * @return Conversion selected from this class, with the given name
	 */
	@SuppressWarnings("unchecked")
	public static Method selectByName(String name)
	{
		Class[] parameterTypes = {Object.class};
		try {
			return PrimitiveTypeConversions.class.getMethod(name, parameterTypes);
		} catch (NoSuchMethodException e) {
			try {
				return PrimitiveTypeConversions.class.getMethod("identity", parameterTypes);
			} catch (NoSuchMethodException e1) {	// Cannot be thrown (and if it is anyway, cannot be handled
				return null;
			}
		}
	}
	
	public static Object identity(Object i) {
		return i;
	}
	
	public static String toString(Object i) {
		return i.toString();
	}
	
	public static Integer floatToInt(Object i) {
		return ((Float)i).intValue();
	}
	public static Integer stringToInt(Object i) {
		try {
			return Integer.parseInt((String)i);
		} catch(NumberFormatException e) {
			System.err.println("Failed to parse '"+i+"' to an Integer");
			return new Integer(0);
		}
	}
	
	public static Float intToFloat(Object i) {
		return ((Integer)i).floatValue();
	}
	public static Float stringToFloat(Object i) {
		try {
			return Float.parseFloat((String)i);
		} catch(NumberFormatException e) {
			System.err.println("Failed to parse '"+i+"' to a float");
			return new Float(0);
		}
	}
}
