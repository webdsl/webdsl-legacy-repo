package transformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class AttributeConversion extends UntypedTransformation {
	private final String attributeName;
	private final Method conversion;
	
	public AttributeConversion(String attributeName, Method conversion) {
		this.attributeName = attributeName;
		this.conversion = conversion;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		if(attributeName.equals(this.attributeName))
		{
			// Convert
			Object[] args = new Object[1];
			args[0] = hd(input).getAttribute(tl(input), scope, attributeName);
			try {
				return conversion.invoke(null, args);
			} catch (IllegalArgumentException e) {
				throw new TransformationException("Attribute conversion does not accept single argument of type "+args[0].getClass().getName(), e);
			} catch (IllegalAccessException e) {
				throw new TransformationException("Access to attribute conversion denied", e);
			} catch (InvocationTargetException e) {
				throw new TransformationException("Attribute conversion threw an exception", e);
			} catch (NullPointerException e) {
				throw new TransformationException("Attribute conversion resulted in an NullPointerException, is the given conversion static?", e);
			}
		}
		return hd(input).getAttribute(tl(input), scope, attributeName);
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @return the conversion
	 */
	public Method getConversion() {
		return conversion;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1 + hd(inputs).getNrInputs(scope, tl(inputs));
	}
}
