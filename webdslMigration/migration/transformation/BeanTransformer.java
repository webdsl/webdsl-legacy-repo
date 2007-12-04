package transformation;

import java.util.List;

public class BeanTransformer implements Transformer {
	private final Transformation trans;
	private final Class targetClass; 
	
	public BeanTransformer(Transformation trans, Class targetClass) {
		this.trans = trans;
		this.targetClass = targetClass;
	}
	
	public Object transform(List<Object> input) throws TransFormationException {
		try {
			Object trafoResult = targetClass.newInstance();
			trans.transForm(input, trafoResult);
			return trafoResult;
		} catch (InstantiationException e) {
			throw new TransFormationException("Could not instantiate result of transformation", e);
		} catch (IllegalAccessException e) {
			throw new TransFormationException("Could not instantiate result of transformation (constructor restricted)", e);
		}
	}

	public List<Injection> getInjections() {
		return trans.getInjections();
	}
}
