package transformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

public class Injection extends UntypedTransformation {	// Cannot use generics, as the type paramter cannot be evaluated at runtime
	private Class injectionType;
	
	public Injection(Class injectionType) {
		this.injectionType = injectionType;
	}
	
	/**
	 * @return the injectionType
	 */
	public Class getInjectionType() {
		return injectionType;
	}
	
	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		if(input.size() == 0)
			throw new TransFormationException("No input left when requesting attribute of injection transformation (not enough inputs supplied to a transformation?)");
		try {
			Object currentInput = input.remove(0);
			Method getter = currentInput.getClass().getMethod(attributeNameToGetter(attributeName), new Class[0]);
			return getter.invoke(currentInput, new Object[0]);
			
		} catch (SecurityException e) {
			throw new TransFormationException("Cannot access getter for "+attributeName, e);
		} catch (NoSuchMethodException e) {
			throw new TransFormationException("No getter for "+attributeName, e);
		} catch (IllegalArgumentException e) {
			throw new TransFormationException("Getter for "+attributeName + " requires arguments", e);
		} catch (IllegalAccessException e) {
			throw new TransFormationException("Cannot access getter for "+attributeName, e);
		} catch (InvocationTargetException e) {
			throw new TransFormationException("Getter for "+attributeName+" threw an exception", e);
		}
	}
	
	@Override
	public List<Injection> getInjections() {
		Vector<Injection> res = new Vector<Injection>();
		res.add(this);
		return res;
	}
	
	private String attributeNameToGetter(String attributeName)
	{
		String firstChar = attributeName.substring(0, 1);
		String tail = attributeName.substring(1, attributeName.length());
		return "get" + firstChar.toUpperCase() + tail;
	}
}
