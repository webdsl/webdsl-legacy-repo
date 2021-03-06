package transformation;

import java.util.List;

public class Identity extends UntypedTransformation {
	public Identity() {}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		return hd(input).getAttribute(tl(input), scope, attributeName);
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1 + hd(inputs).getNrInputs(scope, tl(inputs));
	}
}
