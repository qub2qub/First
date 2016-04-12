package ru.mars.Generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Box<T> {
    // T stands for "Type"
    private T t;
    public void set(T t) { this.t = t; }
    public T get() { return t; }
    

    
    public static void main(String[] args) {
		Box<Integer> bi = new Box<>();
		Box<String> bs = new Box<>();
		System.out.println("bi="+bi.getClass());
		System.out.println("bs="+bs.getClass());
		System.out.println("bi==bs --> "+bs.getClass().equals(bi.getClass()));
		
		Box2<Integer> bi2 = new Box2<>(11);
		Box2<Float> bf2 = new Box2<>(22.33f);
		bf2.set(1.1f);
		System.out.println("bfs="+bf2.getInt());
		
		List rawl = new ArrayList();
		List<Object> obj = new ArrayList<>();
		List<String> strl = new ArrayList<>();
		obj.add(new Date());
		obj.add(new Box2<Integer>(2));
		strl.add("ABC");
		rawl=strl;
//		strl=obj;
//		obj=strl;
		rawl.add(8);
		System.out.println("raw list="+rawl);
		System.out.println("string list="+strl);
//		System.out.println("raw list get ="+rawl.get(0));
//		System.out.println("string list get ="+strl.get(1));
//		
		ShowCollection sc = new ShowCollection();
		System.out.println("TEST:");
		sc.test(Arrays.asList("TEST"));
		System.out.println("String List:");
		sc.test(strl);
		System.out.println("Obj List:");
		sc.test(obj);
		System.out.println("-----------------");
		List<Integer> li = Arrays.asList(1, 2, 3);
		List<String>  ls = Arrays.asList("one", "two", "three");
		sc.printList(li);
		sc.printList(ls);

		
	}
}
