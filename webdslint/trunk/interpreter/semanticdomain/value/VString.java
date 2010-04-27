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
	
	public boolean equals(VString other) {
		if (value == null)
			return false;
		else if (other == null)
			return false;
		else if (other.value == null)
			return false;
		else
			return value.equals(other.value);
	}
	
	public int hashCode() {
		if (value == null)
			return -1;
		else
			return value.hashCode();
	}

	public String toString() {
		return value;
	}
}
