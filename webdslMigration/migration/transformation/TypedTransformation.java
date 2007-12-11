package transformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class TypedTransformation extends UntypedTransformation{
	public abstract Object transform(List<Object> input) throws TransformationException;
	public abstract Class getType();
	
	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransformationException {
		Object res = transform(input);
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
