package ru.mars.HashEquals;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class BigDecimalCompare {
	public static void main(String[] args) {

		BigDecimal bd1 = new BigDecimal("2.0");
		BigDecimal bd2 = new BigDecimal("2.00");
		System.out.println("comparing BigDecimal using equals: " + bd1.equals(bd2));
		System.out.println("comparing BigDecimal using compareTo: " + bd1.compareTo(bd2));
		//BigDecimal equals: false
		//BigDecimal compareTo: 0
		//HashSet uses equals to check duplicates
		//TreeSet uses compareTo to check duplicates. 
		Set<BigDecimal> set = new HashSet<>();
		set.add(bd1);
		set.add(bd2);
		for (BigDecimal bigDecimal : set) {
			System.out.println("HashSet="+bigDecimal);
		}
		Set<BigDecimal> tset = new TreeSet<>();
		tset.add(bd1);
		tset.add(bd2);
		for (BigDecimal bigDecimal : tset) {
			System.out.println("TreeSet="+bigDecimal);
		}
		
	}
}
