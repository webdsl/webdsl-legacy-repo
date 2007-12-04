package migration;

import transformation.Transformation;

public class HibernateTransFormerClassPair
{
	private final Transformation transformation;
	private final Class targetClass;
	
	public HibernateTransFormerClassPair(Transformation transformation, Class targetClass) {
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
	public Transformation getTransformation() {
		return transformation;
	}
}