package transformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Many to one relation, attribute moves from 'one-side' to 'many-side'
 * @author sander
 *
 */
public class ElevateAttributeOverReference extends Transformation {
	private final Transformation inputTrafo;
	private final String attributeName;
	private final String referenceName;
	
	public ElevateAttributeOverReference(String attributeName, String referenceName, Transformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.attributeName = attributeName;
		this.referenceName = referenceName;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		if(attributeName.equals(this.attributeName))
		{
			try {
				Object reference = inputTrafo.getAttribute(input, referenceName);
				Method getter = reference.getClass().getMethod(getGetterFromAttributeName(attributeName), new Class[0]);
				return getter.invoke(reference, new Object[0]);
			} catch (SecurityException e) {
				throw new TransFormationException("Could not access the referenced object's attribute "+attributeName+" to descend its value", e);
			} catch (NoSuchMethodException e) {
				throw new TransFormationException("Could not access the referenced object's attribute "+attributeName+" to descend its value (no getter defined)", e);
			} catch (IllegalArgumentException e) {
				throw new TransFormationException("Could not access the referenced object's attribute "+attributeName+" to descend its value (getter does not accept empty parameterlist)", e);
			} catch (IllegalAccessException e) {
				throw new TransFormationException("Could not access the referenced object's attribute "+attributeName+" to descend its value", e);
			} catch (InvocationTargetException e) {
				throw new TransFormationException("Could not get attribute to descend, getter of attribute "+attributeName+" resulted in an exception", e);
			}
		}
		return inputTrafo.getAttribute(input, attributeName);
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @return the input transformation
	 */
	public Transformation getInputTrafo() {
		return inputTrafo;
	}

	/**
	 * @return the referenceName
	 */
	public String getReferenceName() {
		return referenceName;
	}

	@Override
	public List<Injection> getInjections() {
		return inputTrafo.getInjections();
	}
	
	private static String getGetterFromAttributeName(String attName)
	{
		String firstChar = attName.substring(0, 1);
		String tail = attName.substring(1, attName.length());
		return "get" + firstChar.toUpperCase() + tail;
	}
}
