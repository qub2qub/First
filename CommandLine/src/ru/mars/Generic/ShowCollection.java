package ru.mars.Generic;

import java.util.Collection;
import java.util.List;

public class ShowCollection {
	
//	public <E> void test(Collection<E> c) {
//		System.out.println("test Collection<E>");
//		for (E element : c) {
//			System.out.println(element);
//		}
//	}
	
	public <String> void test(Collection<String> c) {
		System.out.println("test Collection<String>");
		for (String element : c) {
			System.out.println(element);
		}
	}
	
	public void test(List<Integer> c) {
		System.out.println("test List<Integer>");
		for (Integer integer : c) {
			System.out.println(integer);
		}
	}
	
	public void printList(List<?> list) {
	    for (Object elem: list)
	        System.out.print(elem + " ");
	    System.out.println();
	}


}
