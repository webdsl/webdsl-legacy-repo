package transformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Augments the untyped transformation with a type.
 * Any class extending this one is required to implement a transform method that will transform 
 * a given Object to a new Object and a getType method. The getAttribute method from 
 * UntypedTransformation is overridden by a default implementation that makes use of the Bean 
 * convention (specifically getter names). Override this method if this default convention does 
 * not apply to your type context.
 * @author sander
 *
 */
public abstract class TypedTransformation extends UntypedTransformation{
	public abstract Object transform(List<UntypedTransformation> input, TransformationScope scope) throws TransformationException;
	
	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		Object res = transform(input, scope);
		try {
			Method getter = res.getClass().getDeclaredMethod(getGetterFromAttributeName(attributeName), new Class[0]);
			return getter.invoke(res, new Object[0]);
		} catch (SecurityException e) {
			throw new TransformationException("Requested attribute "+attributeName+" cannot be accessed", e);
		} catch (NoSuchMethodException e) {
			throw new TransformationException("Getter for requested attribute "+attributeName+" does not exist", e);
		}catch (IllegalAccessException e) {
			throw new TransformationException("Requested attribute "+attributeName+" cannot be accessed", e);
		}catch (InvocationTargetException e) {
			throw new TransformationException("Getter to requested attribute "+attributeName+" threw an exception", e);
		}
		
	}
}
