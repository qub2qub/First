package ru.mars.Generic;
public class Camera extends GProduct<Camera> {

	int pix;
	
	public Camera(int id, int pix) {
		super(id, "Cam#"+id);
		this.pix = pix;
	}
	public int getPix() {
		return pix;
	}
	public void setPix(int pix) {
		this.pix = pix;
	}
	@Override
	int subCompare(Camera o) {
		System.out.println("Camera subCompare");
		if (o.getClass() == this.getClass()) {
			return Integer.compare(this.getPix(), ((Camera)o).getPix());
		}
		return 1;
	}
	
	@Override
	public String toString() {
		return super.toString()+"Pix="+pix;
	}

}