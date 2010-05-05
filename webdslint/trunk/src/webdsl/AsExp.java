package webdsl;

public class AsExp extends Exp implements Cloneable {

	Element element;

	public AsExp(Element element) {
		this.element = element;
	}

	public String pp(String indent) {
		return "asexp " + element.pp(indent);
	}

}
