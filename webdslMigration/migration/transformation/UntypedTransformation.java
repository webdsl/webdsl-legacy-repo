package transformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;
 
public abstract class UntypedTransformation{
	public abstract Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException;
	public abstract int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException;
	
	public void transForm(List<UntypedTransformation> input, TransformationScope scope, Object output, String[] attributeNames) throws TransformationException
	{
		for(String attName : attributeNames)
		{
			// Copy input, to make sure the current input remains unchanged and can be reused
			List<UntypedTransformation> inputCopy = new Vector<UntypedTransformation>(input);
			Object transformedAttribute = getAttribute(inputCopy, scope, attName);
			
			System.out.println("Setting attribute "+attName+" to "+transformedAttribute);			
			try
			{
				// Find setter
				Class[] paramTypes = new Class[1];
				paramTypes[0] = transformedAttribute.getClass();
				Method m = output.getClass().getMethod(getSetterFromAttributeName(attName), paramTypes);			
			
				// Call setter
				Object[] params = new Object[1];
				params[0] = transformedAttribute;
				m.invoke(output, params);
			} catch (IllegalArgumentException e) {
				throw new TransformationException("Target of transformation has a setter ("+getSetterFromAttributeName(attName)+") that does not accept a parameter of type "+transformedAttribute.getClass()+", which resulted from the given transformation (are the source domain, transformation and target domain compatible?)", e);
			} catch (IllegalAccessException e) {
				throw new TransformationException("Cannot access setter "+getSetterFromAttributeName(attName)+" of type "+output.getClass(), e);
			} catch (InvocationTargetException e) {
				throw new TransformationException("Setter "+getSetterFromAttributeName(attName)+" threw an exception during transformation", e);
			} catch (SecurityException e) {
				throw new TransformationException("Could not access type "+output.getClass()+" to find a setter for attribute "+attName, e);
			} catch (NoSuchMethodException e) {
				throw new TransformationException("Did not find a setter for attribute "+attName+" in type "+output.getClass(), e);
			}
		}
	}
	
	/**
	 * Will transform the given input object into the given output object, according to the defined transformation. 
	 * Be careful when using this method: The editable fields are determined based upon the setters of the output object. 
	 * If a setter has not been defined for a specific attribute, it will be ignored during transformation. To be sure the 
	 * right part of the output object is transformed, use the attributeNames-parametrized version of transform in this 
	 * class. 
	 * @param input Input to the transformation
	 * @param output Output of the transformation (the contents of this parameter may be overwritten)
	 * @throws TransformationException If the transformation can not be completed correctly
	 */
	public void transForm(List<UntypedTransformation> input, TransformationScope scope, Object output) throws TransformationException
	{
		// Select right methods (all setters)
		Vector<String> editableAtts= new Vector<String>();
		Method[] targetMethods = output.getClass().getMethods();
		for(Method m : targetMethods)
		{
			// Only use setters
			if(m.getName().startsWith("set"))
				editableAtts.add(getAttributeNameFromSetter(m));
		}
		
		// Transform
		transForm(input, scope, output, editableAtts.toArray(new String[editableAtts.size()]));
	}

	protected static String getAttributeNameFromSetter(Method setter)
	{
		String name = setter.getName();
		String firstChar = name.substring(3, 4);
		String tail = name.substring(4, name.length());
		return firstChar.toLowerCase() + tail;
	}
	
	protected static String getSetterFromAttributeName(String attName)
	{
		String firstChar = attName.substring(0, 1);
		String tail = attName.substring(1, attName.length());
		return "set" + firstChar.toUpperCase() + tail;
	}
	
	protected static String getGetterFromAttributeName(String attName)
	{
		String firstChar = attName.substring(0, 1);
		String tail = attName.substring(1, attName.length());
		return "get" + firstChar.toUpperCase() + tail;
	}
	
	protected void shouldBeTypedTransformation(UntypedTransformation t) throws TransformationException
	{
		if(!(t instanceof TypedTransformation))
			throw new TransformationException("Input to the " + this.getClass().getName() + " transformation should be a typed transformation");
	}
	
	protected List<UntypedTransformation> tl (List<UntypedTransformation> i) {
		return i.subList(1, i.size());
	}
	
	protected UntypedTransformation hd (List<UntypedTransformation> i) {
		return i.get(0);
	}
}
