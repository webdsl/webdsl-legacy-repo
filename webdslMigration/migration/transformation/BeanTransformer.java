package transformation;

import java.util.List;

public class BeanTransformer extends TypedTransformation {
	private final Class targetClass; 
	
	public BeanTransformer(Class targetClass) {
		this.targetClass = targetClass;
	}
	
	public Object transform(List<UntypedTransformation> input, TransformationScope scope) throws TransformationException {
		try {
			Object trafoResult = targetClass.newInstance();
			hd(input).transForm(tl(input), scope, trafoResult);
			return trafoResult;
		} catch (InstantiationException e) {
			throw new TransformationException("Could not instantiate result of transformation", e);
		} catch (IllegalAccessException e) {
			throw new TransformationException("Could not instantiate result of transformation (constructor restricted)", e);
		}
	}

	public Class getType() {
		return targetClass;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1 + hd(inputs).getNrInputs(scope, tl(inputs));
	}
}
