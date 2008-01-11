package transformation;

import java.util.List;

public class Restrict extends TypedTransformation {
	private final UnaryPredicate pred;
	
	public Restrict(UnaryPredicate pred) {
		this.pred = pred;
	}
	
	@Override
	public Object transform(List<UntypedTransformation> input, TransformationScope scope) throws TransformationException {
		shouldBeTypedTransformation(hd(input));
		Object trafoRes = ((TypedTransformation)hd(input)).transform(tl(input), scope);
		if(!pred.evaluate(trafoRes))
			throw new PartialTransformationException();
		return trafoRes;
	}

	/**
	 * @return the pred
	 */
	public UnaryPredicate getPred() {
		return pred;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1 + hd(inputs).getNrInputs(scope, tl(inputs));
	}	
}
