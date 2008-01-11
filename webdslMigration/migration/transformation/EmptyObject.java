package transformation;

import java.util.List;

public class EmptyObject extends UntypedTransformation {
	
	public EmptyObject() {
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		throw new TransformationException("Attribute "+attributeName+" is not part of the transformation (getAttribute arrived at EmptyObject)");
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 0;
	}
	
}
