package semanticdomain.value;

public class VBoolean extends Value {
	
	private boolean value = false;

	public VBoolean(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
	
	public void setValue(boolean value) {
		this.value = value;
	}
	
	public boolean equals(VBoolean other) {
		return value == other.value;
	}
	
	public String toString() {
		return "" + value;
	}
}
