package ru.mars.Generic;

public class Phone extends GProduct<Phone> {
	int size;

	public Phone(int id, int size) {
		super(id, "Phone#"+id);
		this.size = size;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int pix) {
		this.size = pix;
	}
	@Override
	int subCompare(Phone o) {
		System.out.println("Phone subCompare");
		if (o.getClass() == this.getClass()) {
			return Integer.compare(this.getSize(), ((Phone)o).getSize());
		}
		return 1;
	}

	@Override
	public String toString() {
		return super.toString()+"Size="+size;
	}
}
