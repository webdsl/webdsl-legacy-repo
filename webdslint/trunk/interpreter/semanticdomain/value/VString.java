package semanticdomain.value;

public class VString extends Value {
	
	private String value = null;

	public VString(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
