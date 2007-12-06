package migration;

import transformation.UntypedTransformation;

public class HibernateTransFormerClassPair
{
	private final UntypedTransformation transformation;
	private final Class targetClass;
	
	public HibernateTransFormerClassPair(UntypedTransformation transformation, Class targetClass) {
		this.transformation = transformation;
		this.targetClass = targetClass;
	}

	/**
	 * @return the targetClass
	 */
	public Class getTargetClass() {
		return targetClass;
	}

	/**
	 * @return the transformation
	 */
	public UntypedTransformation getTransformation() {
		return transformation;
	}
}