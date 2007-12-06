package transformation;

public class PartialTransformationException extends TransFormationException {
	public PartialTransformationException() {
		super("Transformation not defined for this combination of inputs");
	}
	
	public PartialTransformationException(String msg) {
		super(msg);
	}
	
	public PartialTransformationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
