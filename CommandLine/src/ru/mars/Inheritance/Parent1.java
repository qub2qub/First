package ru.mars.Inheritance;

public class Parent1 {
	Parent1() {
		System.out.println("Parent1 Constructor");
		init();
	}

	void init() {
		System.out.println("init Parent");
		print(2);
	}

	void print(int/*Integer */value) {
		System.out.println(value);
	}
}
