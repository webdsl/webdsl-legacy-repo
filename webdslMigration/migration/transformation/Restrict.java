package transformation;

import java.util.List;

public class Restrict extends TypedTransformation {
	private final TypedTransformation itrafo;
	private final UnaryPredicate pred;
	
	public Restrict(UnaryPredicate pred, TypedTransformation itrafo) {
		this.itrafo = itrafo;
		this.pred = pred;
	}
	
	@Override
	public Class getType() {
		return itrafo.getType();
	}

	@Override
	public Object transform(List<Object> input) throws TransformationException {
		Object trafoRes = itrafo.transform(input);
		if(!pred.evaluate(trafoRes))
			throw new PartialTransformationException();
		return trafoRes;
	}

	@Override
	public List<Injection> getInjections() {
		return itrafo.getInjections();
	}
	
	/**
	 * @return the itrafo
	 */
	public TypedTransformation getItrafo() {
		return itrafo;
	}

	/**
	 * @return the pred
	 */
	public UnaryPredicate getPred() {
		return pred;
	}	
}
