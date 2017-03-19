package Inheritance;

import java.util.Objects;

public class Child1 extends Parent1 {
//	Integer f = new Integer(3);
	int f;
	Integer k;

	Child1() {
		System.out.println("Child1 Constructor");
		f = 1;
		init();
	}

	void init() {
//		int j;
//		print(j);
		System.out.println("init Child f = " + f);
		print(f);
	}

	static public void main(String[] args) {
		Child1 child = new Child1();
		System.out.println("----------------------------");
		Parent1 parent = new Parent1();
		System.out.println("Objects.equals(parent, child)="+Objects.equals(parent, child));
		System.out.println(" parent.getClass() ==> " + parent.getClass());
		System.out.println(" child.getClass() ==> " + child.getClass());
		System.out.println("child instanceof Parent1 ==> " + (child instanceof Parent1));
		System.out.println("parent instanceof Child1 ==> " + (parent instanceof Child1));
	}

}
