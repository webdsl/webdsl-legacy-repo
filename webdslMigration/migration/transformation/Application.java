package transformation;

import java.util.List;
import java.util.Vector;

public class Application extends UntypedTransformation {
	private UntypedTransformation t1;
	private UntypedTransformation t2;
	
	public Application(UntypedTransformation t1, UntypedTransformation t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		// Check nr inputs
		if(input.size() != getNrInputs(null, null))
			throw new TransformationException("Incorrect number of inputs to transformation (got "+input.size()+", but needed "+getNrInputs(null, null)+")");
		
		Vector<UntypedTransformation> newInput = new Vector<UntypedTransformation>();
		newInput.add(t2);
		newInput.addAll(input);
		return t1.getAttribute(newInput, scope,  attributeName);
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		Vector<UntypedTransformation> newInputs = new Vector<UntypedTransformation>();
		newInputs.add(t2);
		newInputs.addAll(inputs);
		return t1.getNrInputs(scope, newInputs);
	}
	

}
