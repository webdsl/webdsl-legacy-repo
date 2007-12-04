package transformation;

import java.util.List;

public class Identity extends Transformation {
	private final Transformation inputTrafo;
	
	public Identity(Transformation inputTrafo) {
		this.inputTrafo = inputTrafo;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		return inputTrafo.getAttribute(input, attributeName);
	}

	/**
	 * @return the input transformation
	 */
	public Transformation getInputTrafo() {
		return inputTrafo;
	}

	@Override
	public List<Injection> getInjections() {
		return inputTrafo.getInjections();
	}

	
}
