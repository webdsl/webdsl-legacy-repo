package transformation;

import java.util.List;

public class KeepAttributes extends UntypedTransformation {
	private final UntypedTransformation inputTrafo;
	private final String[] attributeNames;
	
	public KeepAttributes(String[] attributeNames, UntypedTransformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.attributeNames = attributeNames;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransformationException {
		if(!kept(attributeName))
			throw new TransformationException("Dropped attribute requested in KeepAttribute (" + attributeName+")");
		return inputTrafo.getAttribute(input, attributeName);
	}

	/**
	 * @return the attributeName
	 */
	public String[] getAttributeNames() {
		return attributeNames;
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
	
	public boolean kept(String att)
	{
		for(String attName : attributeNames)
			if(att.equals(attName))
				return true;
		return false;
	}
}
