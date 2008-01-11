package transformation;

import java.util.List;

/**
 * Like a merge, execpt that the output from the master and slave transformation is 
 * verified by means of a given relation. If successful, the input will be used to
 * merge, if not, the transformation ends by means of a partial transformation exception.
 * @author sander
 *
 */
public class RelatedMerge extends Merge {
	
	private final BinaryPredicate relation;
	
	public RelatedMerge(BinaryPredicate relation) {
		super();
		this.relation = relation;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		UntypedTransformation input1 = hd(input);					// first
		UntypedTransformation input2 = hd(tl(input));				// second
		List<UntypedTransformation> remainingInput = tl(tl(input));	// third and higher
		List<UntypedTransformation> inputToFirst = remainingInput.subList(0, input1.getNrInputs(null, null));
		List<UntypedTransformation> inputToSecond = remainingInput.subList(input1.getNrInputs(null, null), remainingInput.size());	// size should exactly be required number of inputs
		
		shouldBeTypedTransformation(input1);
		shouldBeTypedTransformation(input2);

		// Check 
		Object input1Result = ((TypedTransformation)input1).transform(inputToFirst, scope);
		Object input2Result = ((TypedTransformation)input2).transform(inputToSecond, scope);
		if(!relation.relate(input1Result, input2Result))
			throw new PartialTransformationException();
		// Get
		return super.getAttribute(input, scope, attributeName);
	}

	/**
	 * @return the relation
	 */
	public BinaryPredicate getRelation() {
		return relation;
	}
}
