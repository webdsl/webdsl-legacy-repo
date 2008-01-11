package transformation;

import java.util.List;

public class TransformationCall extends UntypedTransformation {
	String id;
	
	public TransformationCall(String id) {
		this.id = id;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		return scope.getDefinition(id).getAttribute(input, scope, attributeName);
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return scope.getDefinition(id).getNrInputs(scope, inputs);
	}
}
