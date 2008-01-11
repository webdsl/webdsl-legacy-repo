package transformation;

import java.util.List;

public class KeepAttributes extends UntypedTransformation {
	private final String[] attributeNames;
	
	public KeepAttributes(String[] attributeNames) {
		this.attributeNames = attributeNames;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		if(!kept(attributeName))
			throw new TransformationException("Dropped attribute requested in KeepAttribute (" + attributeName+")");
		return hd(input).getAttribute(tl(input), scope, attributeName);
	}

	/**
	 * @return the attributeName
	 */
	public String[] getAttributeNames() {
		return attributeNames;
	}

	public boolean kept(String att)
	{
		for(String attName : attributeNames)
			if(att.equals(attName))
				return true;
		return false;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1 + hd(inputs).getNrInputs(scope, tl(inputs));
	}
}
