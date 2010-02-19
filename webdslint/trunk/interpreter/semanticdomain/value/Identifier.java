package semanticdomain.value;

public class Identifier extends Value {
	
	private String name;
	
	public Identifier(String name) {
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
	
	public boolean equals(Identifier o) {
		return name.equals(o.name);
	}
}
