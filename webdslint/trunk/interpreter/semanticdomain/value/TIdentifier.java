package semanticdomain.value;

public class TIdentifier extends Value {
	
	private String name = null;

	public TIdentifier(String name) {
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

	public boolean equals(TIdentifier o) {
		return name.equals(o.name);
	}
}
