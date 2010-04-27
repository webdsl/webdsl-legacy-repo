package semanticdomain.value;

public class VTIdentifier extends Value {
	
	private String name = null;

	public VTIdentifier(String name) {
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

	public boolean equals(VTIdentifier o) {
		return name.equals(o.name);
	}
}
