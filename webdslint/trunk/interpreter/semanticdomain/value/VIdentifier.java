package semanticdomain.value;

public class VIdentifier extends Value {
	
	private String name;
	
	public VIdentifier(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public boolean equals(VIdentifier o) {
		return name.equals(o.name);
	}
}
