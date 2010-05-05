package webdsl;

import java.util.Iterator;

import beaver.Symbol;

public class List<T extends ASTNode> extends ASTNode implements Cloneable {

	java.util.List<T> items;

	public void add(T def) {
		items.add(def);
	}

	public String pp(String indent, String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String pp(String indent, String string, String string2,
			String string3) {
		// TODO Auto-generated method stub
		return null;
	}


	public Iterator<T> iterator() {
		return items.iterator();
	}

	public int size() {
		return items.size();
	}

	public T get(int i) {
		return items.get(i);
	}


}
