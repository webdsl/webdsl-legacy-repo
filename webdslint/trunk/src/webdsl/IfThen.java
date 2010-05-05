package webdsl;

public class IfThen extends Element implements Cloneable {
	Exp exp;
	Element thenPart;
	public IfThen(Exp exp, Element thenPart) {
		super();
		this.exp = exp;
		this.thenPart = thenPart;
	}
}
