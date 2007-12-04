package transformation;

import java.util.List;

public class AttributeNameChange extends Transformation {
	private final Transformation inputTrafo;
	private final String oldAttributeName;
	private final String newAttributeName;
	
	public AttributeNameChange(String oldAttributeName, String newAttributeName, Transformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.oldAttributeName = oldAttributeName;
		this.newAttributeName = newAttributeName;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		if(attributeName.equals(newAttributeName))
			return inputTrafo.getAttribute(input, oldAttributeName);
		return inputTrafo.getAttribute(input, attributeName);
	}

	/**
	 * @return the newAttributeName
	 */
	public String getNewAttributeName() {
		return newAttributeName;
	}

	/**
	 * @return the oldAttributeName
	 */
	public String getOldAttributeName() {
		return oldAttributeName;
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
