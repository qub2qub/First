package ru.mars.Main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class J1Run {

	
	public static void main(String[] args) {

		int base = 20;
		System.out.println("4<<1 = "+ (4<<1));
		System.out.println("4>>1 = "+ (4>>1));
		System.out.println(base+"b<<1 = "+ (base<<1));
		System.out.println(base+"b>>1 = "+ (base>>1));
		
		/*
		List<Integer> cow = new CopyOnWriteArrayList<>();
		List<Integer> ll = new LinkedList<>();
		List<Integer> list = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			list.add(10*i);
			ll.add(3*i);
			cow.add(5*i);
			System.out.println("add="+ll);
		}
		System.out.println("size="+ll.size());
		for (int i = 0; i < ll.size(); i++) {
			ll.remove(0);
			System.out.println("remove="+ll);
		}
		
		System.out.println("1 cow="+cow+", size="+cow.size());
		Iterator<Integer> iter = cow.iterator();
		while (iter.hasNext()) {
			System.out.println("next="+iter.next());
//			iter.remove();
			cow.add(cow.size());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("2 cow="+cow+", size="+cow.size());
		/*
		System.out.println("list="+list+", size="+list.size());
		System.out.println("LL="+ll+", size="+ll.size());
		 ListIterator<Integer> lit = list.listIterator();
		 ListIterator<Integer> llit = ll.listIterator();
		 while (llit.hasNext()) {
				System.out.println("next="+ llit.nextIndex() +", "+llit.next());
//				q.offer(6);
			}
//		 lit.
		/*
		Queue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 % 2 == 0 && o2 % 2 != 0) {
					return -1;
				} else if (o1 % 2 != 0 && o2 % 2 == 0) {
					return 1;
				} else{
					return Integer.compare(o1, o2);
				}
			}
		});
		Queue<Integer> q = new LinkedList<>();
		Deque<Integer> qq = new LinkedList<>();
		q.offer(4);
		q.offer(5);
		q.offer(1);
		q.offer(2);
		q.offer(3);
		pq.offer(4);
		pq.offer(5);
		pq.offer(1);
		pq.offer(2);
		pq.offer(3);
		System.out.println("q="+q);
		
		Iterator<Integer> iter = q.iterator();
		while (iter.hasNext()) {
			System.out.println("next="+iter.next());
//			q.offer(6);
		}
		while (!q.isEmpty()) System.out.println(q.poll());
		System.out.println("Pq="+pq);
		while (!pq.isEmpty()) System.out.println(pq.poll());
		
		qq.offer(1);
		qq.offer(2);
		qq.offer(3);
		qq.offerFirst(4);
		qq.offerFirst(5);
		System.out.println("qq="+qq);
		System.out.println("qq="+qq.pollLast());
		System.out.println("qq="+qq.pollLast());
		
		/*
		System.out.println("Currency="+Currency.PENNY);
		for(Currency coin: Currency.values()){
	        System.out.println(coin.ordinal()+" )  " + coin);
	}
		EnumMap<Currency, String> em = new EnumMap<>(Currency.class);
		em.put(Currency.PENNY, "A1");
		em.put(Currency.NICKLE, "B2");
		System.out.println("em="+em);

		WeakHashMap<Date, String> whm  = new WeakHashMap<>();
		Date d = new Date();
		whm.put(d, "Some");
		System.out.println("whm="+whm);
		d=null;
		System.gc();
		for (int i = 0; i < 10000; i++) {
			System.out.print("*");
			if (whm.isEmpty()) {
				System.out.println("Map is Empty!");
				break;
			}
		}
		
		/*
		TreeMap<Integer, Integer> tm = new TreeMap<>();
		HashMap<Integer, String> hm = new HashMap<>();
//		LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>(5, 1, true);
		Map<Integer, String> lhm = new MyCache<>(5);
		hm.put(5, "a");
		hm.put(4, "b");
		hm.put(3, "c");
		hm.put(2, "d");
		hm.put(1, "e");
		System.out.println("hm="+hm);
		lhm.put(5, "a");
		lhm.put(4, "b");
		lhm.put(3, "c");
		lhm.put(2, "d");
		lhm.put(1, "e");
		lhm.get(3);
		lhm.get(5);
		lhm.get(1);
		lhm.put(3, "cc");
		lhm.get(1);
		System.out.println("lhm="+lhm);
		lhm.put(6, "F");
		System.out.println("lhm="+lhm);
//		hm.put(1, "a");
//		hm.put(2, "b");
//		hm.put(3, "c");
//		hm.put(4, "d");
//		hm.put(5, "e");
		
		
		/*
		Set<String> hs = new LinkedHashSet<>();
		hs.add("z");
		hs.add("a");
		hs.add("t");
		hs.add("k");
		hs.add("b");
		System.out.println("hs="+hs);
		TreeSet<Integer> ts = new TreeSet<>();
		for (int i = 0; i < 10; i++) {
			ts.add(10*i);
		}
		System.out.println(" ts = " + ts);
		System.out.println("obr = " + ts.descendingSet());
		System.out.println("floor=20="+ts.floor(20));
		System.out.println("lower=20="+ts.lower(20));
		System.out.println("tailset="+ts.tailSet(70));
		System.out.println("headset="+ts.headSet(71));
		
		Queue<String> qq = new LinkedBlockingQueue<>();
		qq.add("a1");
		
		/*
		BigDecimal bd1 = new BigDecimal("2.0");
		BigDecimal bd2 = new BigDecimal("2.00");
		System.out.println("comparing BigDecimal using equals: " + bd1.equals(bd2));
		System.out.println("comparing BigDecimal using compareTo: " + bd1.compareTo(bd2));
		//HashSet uses equals to check duplicates
		//TreeSet uses compareTo to check duplicates. 
		Set<BigDecimal> set = new HashSet<>();
		set.add(bd1);
		set.add(bd2);
		for (BigDecimal bigDecimal : set) {
			System.out.println("set="+bigDecimal);
		}
		Set<BigDecimal> tset = new TreeSet<>();
		tset.add(bd1);
		tset.add(bd2);
		for (BigDecimal bigDecimal : tset) {
			System.out.println("Tree="+bigDecimal);
		}
		*/
		//		float f1 = 9.2f;
		//		float f2 = 4.1f;
		//		float f3= f1/f2;
		//		System.out.println("f3="+f3);
				System.out.println("9/4="+"целая часть="+(9/4) + " и остаток(9%4)="+(9%4));
				System.out.println("17/3="+"целая часть="+(17/3) + " и остаток(17%3)="+(17%3));
		//		
		//		double a = 2.0f - 1.1f;
		//		System.out.println("a="+a);
		//		
		//		double f = 0.0;
		//		for (int i=1; i <= 10; i++) {
		//		    f += 0.1;
		//		}
		//		System.out.println("f="+f);
	}

}
