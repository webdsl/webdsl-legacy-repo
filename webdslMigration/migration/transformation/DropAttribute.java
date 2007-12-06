package transformation;

import java.util.List;

public class DropAttribute extends UntypedTransformation {
	private final UntypedTransformation inputTrafo;
	private final String attributeName;
	
	public DropAttribute(String attributeName, UntypedTransformation inputTrafo) {
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
	public UntypedTransformation getInputTrafo() {
		return inputTrafo;
	}

	@Override
	public List<Injection> getInjections() {
		return inputTrafo.getInjections();
	}
}
