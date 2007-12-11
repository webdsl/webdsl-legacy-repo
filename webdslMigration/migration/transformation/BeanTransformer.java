package transformation;

import java.util.List;

public class BeanTransformer extends TypedTransformation {
	private final UntypedTransformation trans;
	private final Class targetClass; 
	
	public BeanTransformer(UntypedTransformation trans, Class targetClass) {
		this.trans = trans;
		this.targetClass = targetClass;
	}
	
	public Object transform(List<Object> input) throws TransformationException {
		try {
			Object trafoResult = targetClass.newInstance();
			trans.transForm(input, trafoResult);
			return trafoResult;
		} catch (InstantiationException e) {
			throw new TransformationException("Could not instantiate result of transformation", e);
		} catch (IllegalAccessException e) {
			throw new TransformationException("Could not instantiate result of transformation (constructor restricted)", e);
		}
	}

	public List<Injection> getInjections() {
		return trans.getInjections();
	}

	@Override
	public Class getType() {
		return targetClass;
	}
}
