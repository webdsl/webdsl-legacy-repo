package semanticdomain.value;

public class VInt extends Value {
	
	private int value;

	public VInt(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean equals(VInt other) {
		return value == other.value;
	}
	
	public String toString() {
		return "" + value;
	}
}
