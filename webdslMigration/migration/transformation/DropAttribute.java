package transformation;

import java.util.List;

public class DropAttribute extends Transformation {
	private final Transformation inputTrafo;
	private final String attributeName;
	
	public DropAttribute(String attributeName, Transformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.attributeName = attributeName;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		if(attributeName.equals(this.attributeName))
			throw new TransFormationException("Dropped attribute requested (" + attributeName+")");
		return inputTrafo.getAttribute(input, attributeName);
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
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
