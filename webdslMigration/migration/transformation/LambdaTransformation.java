package transformation;

import java.util.List;

public class LambdaTransformation extends UntypedTransformation {
	private final String[] paramNames;
	private final UntypedTransformation trafo;
	
	public LambdaTransformation(String[] paramNames, UntypedTransformation trafo) {
		this.paramNames = paramNames;
		this.trafo = trafo;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		// Define new scope
		TransformationScope newScope = new TransformationScope(scope);
		for(int p=0; p<paramNames.length; p++)
			newScope.setDefinition(paramNames[p], input.get(p));
		
		// Define new input
		List<UntypedTransformation> newInput = input.subList(paramNames.length, input.size());
		
		return trafo.getAttribute(newInput, newScope, attributeName);
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		// Define new scope
		TransformationScope newScope = new TransformationScope(scope);
		for(int p=0; p<paramNames.length; p++)
			newScope.setDefinition(paramNames[p], inputs.get(p));
		List<UntypedTransformation> newInputs = inputs.subList(paramNames.length, inputs.size());
		return trafo.getNrInputs(scope, newInputs);
	}
}
