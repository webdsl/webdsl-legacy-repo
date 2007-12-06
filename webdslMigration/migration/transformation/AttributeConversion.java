package transformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class AttributeConversion extends UntypedTransformation {
	private final UntypedTransformation inputTrafo;
	private final String attributeName;
	private final Method conversion;
	
	public AttributeConversion(String attributeName, Method conversion, UntypedTransformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.attributeName = attributeName;
		this.conversion = conversion;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		if(attributeName.equals(this.attributeName))
		{
			// Convert
			Object[] args = new Object[1];
			args[0] = inputTrafo.getAttribute(input, attributeName);
			try {
				return conversion.invoke(null, args);
			} catch (IllegalArgumentException e) {
				throw new TransFormationException("Attribute conversion does not accept single argument of type "+args[0].getClass().getName(), e);
			} catch (IllegalAccessException e) {
				throw new TransFormationException("Access to attribute conversion denied", e);
			} catch (InvocationTargetException e) {
				throw new TransFormationException("Attribute conversion threw an exception", e);
			} catch (NullPointerException e) {
				throw new TransFormationException("Attribute conversion resulted in an NullPointerException, is the given conversion static?", e);
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
	public UntypedTransformation getInputTrafo() {
		return inputTrafo;
	}

	/**
	 * @return the conversion
	 */
	public Method getConversion() {
		return conversion;
	}
	
	@Override
	public List<Injection> getInjections() {
		return inputTrafo.getInjections();
	}
}
