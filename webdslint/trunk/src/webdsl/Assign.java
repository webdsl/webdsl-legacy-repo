package webdsl;

public class Assign extends Statement implements Cloneable {
	Exp targetl;
	Exp source;

	public Assign(Exp targetl, Exp source) {
		super();
		this.targetl = targetl;
		this.source = source;
	}

	public String pp(String indent) {
		return targetl.pp(indent) + " := " + source.pp(indent) + ";";
	}

}
