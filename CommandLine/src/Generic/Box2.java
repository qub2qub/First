package Generic;

import java.util.ArrayList;
import java.util.List;

class Box2<T extends Number> {
    private T t;
    
    public Box2(T t) {
		super();
		this.t = t;
	}
	public void set(T t) { this.t = t; }
    public T get() { return t; }
    public int getInt() { return t.intValue(); }
    
    // ������ ��� ������� ����� ������������ � ����� �����
    public static boolean findProduct(List<GProduct> all, GProduct p) {
    	for (GProduct gp : all) {
			if (gp.equals(p)) {
				return true;
			}
		}
    	return false;
    }
    
    // ����� ��� �������� ����� ������������ � ����� ������ ����� ��������
    public static boolean findQ(List<? extends GProduct> all, GProduct p) {
    	for (GProduct gp : all) {
    		if (gp.equals(p)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    // ���� � ��� �� ��� �������� ������������ � ����� �� �����
    // ������ ���� ���������� ������
    public static <T extends GProduct> boolean find(List<T> all, T p) {
    	for (GProduct gp : all) {
    		if (gp.equals(p)) {
    			return true;
    		}
    	}
    	return false;
    }
    
//    public static void copy (List<? extends GProduct> inSrc, List<? extends GProduct> outDest) {
    public static void copy (List<? extends GProduct> inSrc, List<? super GProduct> outDest) {
    	for (GProduct src : inSrc) {
    		outDest.add(src);
//    		copy(outDest, src);
    	}
    }
    
    public static <T extends GProduct> void copy (List<T> output, T item) {
    	output.add(item);
    }
    
    @Override
    public String toString() {
    	return "B2str="+t.toString();
    }
    
    public static void main(String[] args) {
		Camera c1 = new Camera(1, 1);
		Camera c2 = new Camera(2, 2);
		Camera c3 = new Camera(3, 3);
		Camera c4 = new Camera(4, 3);
		Phone p1 = new Phone(3, 3);
		Phone p2 = new Phone(4, 4);
		Phone p3 = new Phone(5, 5);
		Phone p4 = new Phone(5, 5);
		Phone p5 = new Phone(4, 1);
		List<Camera> cams= new ArrayList<>();
		cams.add(c1);
		cams.add(c2);
		cams.add(c3);
		cams.add(c4);
		List<Phone> pos= new ArrayList<>();
		pos.add(p1);
		pos.add(p2);
		pos.add(p3);
		pos.add(p4);
		pos.add(p5);
		List<GProduct> list = new ArrayList<>();
		list.addAll(cams);
		list.addAll(pos);
		System.out.println("List="+list);

		// Question <?>
		System.out.println("1Q ALL find Phone="+Box2.findQ(list, p1));
		System.out.println("1Q ALL find Camera="+Box2.findQ(list, c1));
		System.out.println("1Q Cams find="+Box2.findQ(cams, new Camera(5, 1)));
		System.out.println("1Q Phones find="+Box2.findQ(pos, p1));
		System.out.println("1Q Cams find="+Box2.findQ(cams, p2));
		System.out.println("1Q Phones find="+Box2.findQ(pos, c2));
		
		// Only GProduct
		System.out.println("Only GProduct ALL find Phone="+Box2.find(list, p1));
		System.out.println("Only GProduct ALL find Camera="+Box2.find(list, c1));
		
		// Only 1 type
//		System.out.println("Cams find="+Box2.find(cams, p1));
		System.out.println("1T Cams find="+Box2.find(cams, new Camera(5, 1)));
//		System.out.println("Cams find="+Box2.find(cams, new GProduct(6, "Product")));
		System.out.println("1T Phones find="+Box2.find(pos, p1));
//		System.out.println("Cams find="+Box2.findProduct(cams, p1));
//		System.out.println("Phones find="+Box2.findProduct(phns, p1));
		//--------------------------------------
		List<GProduct> src= new ArrayList<>();
		src.add(c1);
		src.add(c2);
		List<GProduct> dst= new ArrayList<>();
		dst.add(p1);
		dst.add(p2);
		Box2.copy(src, dst);
		System.out.println("DEST="+dst);
		//--------------------------------------
		List<Camera> cam2 = new ArrayList<>();
//		List<GProduct> prod = cam2; // list of cam i list of prod is different types
		List<? extends GProduct> prod2 = cam2; // can assign
//		prod2.add(new Camera(6)); // canNOT add new elem
		//--------------------------------------
		for (Camera cam : cams) {
			System.out.println("cam 3 = "+c3.compareTo(cam));
		}
		for (Phone po : pos) {
			System.out.println("Phone 5 = "+p5.compareTo(po));
			
		}
//		System.out.println("p1 - c3 (equal IDs) " + p1.compareTo(c3));
	}
    
    
}