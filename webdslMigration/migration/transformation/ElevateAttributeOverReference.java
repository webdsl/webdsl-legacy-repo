package transformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Many to one relation, attribute moves from 'one-side' to 'many-side'
 * @author sander
 *
 */
public class ElevateAttributeOverReference extends UntypedTransformation {
	private final String attributeName;
	private final String referenceName;
	
	public ElevateAttributeOverReference(String attributeName, String referenceName) {
		this.attributeName = attributeName;
		this.referenceName = referenceName;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		if(attributeName.equals(this.attributeName))
		{
			try {
				Object reference = hd(input).getAttribute(tl(input), scope, referenceName);
				Method getter = reference.getClass().getMethod(getGetterFromAttributeName(attributeName), new Class[0]);
				return getter.invoke(reference, new Object[0]);
			} catch (SecurityException e) {
				throw new TransformationException("Could not access the referenced object's attribute "+attributeName+" to descend its value", e);
			} catch (NoSuchMethodException e) {
				throw new TransformationException("Could not access the referenced object's attribute "+attributeName+" to descend its value (no getter defined)", e);
			} catch (IllegalArgumentException e) {
				throw new TransformationException("Could not access the referenced object's attribute "+attributeName+" to descend its value (getter does not accept empty parameterlist)", e);
			} catch (IllegalAccessException e) {
				throw new TransformationException("Could not access the referenced object's attribute "+attributeName+" to descend its value", e);
			} catch (InvocationTargetException e) {
				throw new TransformationException("Could not get attribute to descend, getter of attribute "+attributeName+" resulted in an exception", e);
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
	 * @return the referenceName
	 */
	public String getReferenceName() {
		return referenceName;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1 + hd(inputs).getNrInputs(scope, tl(inputs));
	}
}
