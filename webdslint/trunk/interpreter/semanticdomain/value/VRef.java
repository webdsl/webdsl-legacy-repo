package semanticdomain.value;

public class VRef extends Value {
	
	private Value vold = null;
	private Value vnew = null;
	private VAddress address = null;

	public VRef(Value vold, Value vnew, VAddress address) {
		this.vold = vold;
		this.vnew = vnew;
		this.address = address;
	}
	
	public VAddress getAddress() {
		return this.address;
	}
	
	public Value getNew() {
		return this.vnew;
	}
	
	public Value getOld() {
		return this.vold;
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
