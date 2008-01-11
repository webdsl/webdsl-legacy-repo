package transformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Injection extends TypedTransformation {	// Cannot use generics, as the type paramter cannot be evaluated at runtime
	private Object injected;
	
	public Injection(Object injected) {
		this.injected = injected;
	}
	
	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		try {
			Method getter = injected.getClass().getMethod(attributeNameToGetter(attributeName), new Class[0]);
			return getter.invoke(injected, new Object[0]);
			
		} catch (SecurityException e) {
			throw new TransformationException("Cannot access getter for "+attributeName, e);
		} catch (NoSuchMethodException e) {
			throw new TransformationException("No getter for "+attributeName, e);
		} catch (IllegalArgumentException e) {
			throw new TransformationException("Getter for "+attributeName + " requires arguments", e);
		} catch (IllegalAccessException e) {
			throw new TransformationException("Cannot access getter for "+attributeName, e);
		} catch (InvocationTargetException e) {
			throw new TransformationException("Getter for "+attributeName+" threw an exception", e);
		}
	}
	
	private String attributeNameToGetter(String attributeName)
	{
		String firstChar = attributeName.substring(0, 1);
		String tail = attributeName.substring(1, attributeName.length());
		return "get" + firstChar.toUpperCase() + tail;
	}

	public Class getType() {
		return injected.getClass();
	}
	
	/**
	 * @return the injected
	 */
	public Object getInjected() {
		return injected;
	}

	@Override
	public Object transform(List<UntypedTransformation> input, TransformationScope scope) throws TransformationException {
		return injected;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 0;
	}
}
