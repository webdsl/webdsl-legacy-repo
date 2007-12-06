package transformation;

import java.util.List;
import java.util.Vector;

public class EmptyObject extends UntypedTransformation {
	
	public EmptyObject() {
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		throw new TransFormationException("Attribute "+attributeName+" is not part of the transformation (getAttribute arrived at EmptyObject)");
	}
	
	@Override
	public List<Injection> getInjections() {
		return new Vector<Injection>();
	}
}
