package Generic;

//public abstract class GProduct<T extends GProduct<T> implements Comparable<IGProduct> {
public abstract class GProduct<T extends IGProduct> implements IGProduct, Comparable<T> {

	int id = 0;
	String name="";
	public GProduct(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	abstract int subCompare(T o);

	@Override
	public int compareTo(T o) {
		int first = Integer.compare(this.getId(), o.getId());
		if (first != 0) {
			System.out.println("product compare = " + first);
			return first;
		} else {
			return subCompare((T) o); // ����� class cast exception
		}
	}

	@Override
	public String toString() {
		return "\n"+this.getClass() +" [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
