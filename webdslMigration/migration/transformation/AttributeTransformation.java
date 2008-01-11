package transformation;

import java.util.List;
import java.util.Vector;

public class AttributeTransformation extends UntypedTransformation {
	private final String attributeName;
	private final TypedTransformation attTrans;
	
	public AttributeTransformation(String attributeName, TypedTransformation attTrans) {
		this.attributeName = attributeName;
		this.attTrans = attTrans;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		if(attributeName.equals(this.attributeName))
		{
			List<UntypedTransformation> transformInput = new Vector<UntypedTransformation>();
			transformInput.add(new Injection(hd(input).getAttribute(tl(input), scope, attributeName)));
			return attTrans.transform(transformInput, scope);
		}
		return hd(input).getAttribute(tl(input), scope, attributeName);
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @return the attTrans
	 */
	public TypedTransformation getAttTrans() {
		return attTrans;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1 + hd(inputs).getNrInputs(scope, tl(inputs));
	}
}
