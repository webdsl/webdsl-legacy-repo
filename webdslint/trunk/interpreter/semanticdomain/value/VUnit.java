package semanticdomain.value;

public class VUnit extends Value {
	
	public static VUnit unit = new VUnit();
	
	private VUnit() {
	}
	
	public String toString() {
		return "unit";
	}
	
	public boolean equals(VUnit o) {
		return true;
	}
}
