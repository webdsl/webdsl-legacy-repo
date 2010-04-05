package semanticdomain.value;

public class Address extends Value {
	
	private String value = null;

	public Address(String value) {
		this.value = value;
	}
	
	public String getAddress() {
		return this.value;
	}
	
	public void setAddress(String address) {
		this.value = address;
	}

	public String toString() {
		return value;
	}
}
