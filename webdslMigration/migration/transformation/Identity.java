package transformation;

import java.util.List;

public class Identity extends UntypedTransformation {
	private final UntypedTransformation inputTrafo;
	
	public Identity(UntypedTransformation inputTrafo) {
		this.inputTrafo = inputTrafo;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		return inputTrafo.getAttribute(input, attributeName);
	}

	/**
	 * @return the input transformation
	 */
	public UntypedTransformation getInputTrafo() {
		return inputTrafo;
	}

	@Override
	public List<Injection> getInjections() {
		return inputTrafo.getInjections();
	}

	
}
