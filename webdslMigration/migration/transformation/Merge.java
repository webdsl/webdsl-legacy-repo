package transformation;

import java.util.List;

public class Merge extends UntypedTransformation {
	public Merge() {}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		UntypedTransformation input1 = hd(input);					// first
		UntypedTransformation input2 = hd(tl(input));				// second
		List<UntypedTransformation> remainingInput = tl(tl(input));	// third and higher
		List<UntypedTransformation> inputToFirst = remainingInput.subList(0, input1.getNrInputs(null, null));
		List<UntypedTransformation> inputToSecond = remainingInput.subList(input1.getNrInputs(null, null), remainingInput.size());	// size should exactly be required number of inputs
		
		// Try master, if not succesful, use slave (possibly throws exception)
		try {
			return input1.getAttribute(inputToFirst, scope, attributeName);}
		catch(TransformationException e){}
		
		return input2.getAttribute(inputToSecond, scope, attributeName);
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		UntypedTransformation nr0 = hd(inputs);
		UntypedTransformation nr1 = hd(tl(inputs));
		List<UntypedTransformation> tl = tl(tl(inputs));
		
		int nrInputs0 = nr0.getNrInputs(scope, tl);
		// Remove inputs that were meant for first trafo
		List<UntypedTransformation> inputtlPart1 = tl.subList(nrInputs0, tl.size());
		int nrInputs1 = nr1.getNrInputs(scope, inputtlPart1);
		return 2 + nrInputs0 + nrInputs1;
	}
	

}
