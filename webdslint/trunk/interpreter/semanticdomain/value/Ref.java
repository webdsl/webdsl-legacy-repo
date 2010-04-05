package semanticdomain.value;

public class Ref extends Value {
	
	private Value vold = null;
	private Value vnew = null;
	private Address address = null;

	public Ref(Value vold, Value vnew, Address address) {
		this.vold = vold;
		this.vnew = vnew;
		this.address = address;
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public String toString() {
		String voldString = "-", vnewString = "-";
		if (vold != null)
			voldString = vold.toString();
		if (vnew != null)
			vnewString = vnew.toString();
		return "<" + voldString + ", " + vnewString + ", " + address + ">";
	}
}
