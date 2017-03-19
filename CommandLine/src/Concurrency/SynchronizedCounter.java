package Concurrency;

import java.util.concurrent.ThreadLocalRandom;

public class SynchronizedCounter {
	private int c = 0;

	public int getCounter() {
		return c;
	}
	public synchronized void inc(int i) {
		System.out.print("1++incStart "+i + " ["+c+"]   ");
		c++;
		System.out.println("2++incEnd "+i + " ["+c+"]");
//		try {
//			Thread.sleep(499);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	public synchronized void dec(int i) {
		System.out.print("3--decStart "+i+ " ["+c+"]   ");
		c--;
		System.out.println("4--decEnd "+i+ " ["+c+"]");
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) {

		int r = ThreadLocalRandom.current().nextInt(4, 77);

		final SynchronizedCounter counter = new SynchronizedCounter();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 15; i++) {
					counter.inc(i);
					System.out.println(i+") INC="+counter.getCounter());
				}
			}
		}, "INC_T");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 15; i++) {
					counter.dec(i);
					System.out.println(i+") DEC="+counter.getCounter());
					
				}
			}
		}, "DEC_T");
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 35; i++) {
					System.out.println("-----------------"+counter.getCounter());
//					try {
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
			}
		}, "GET_T");
		
		t3.start();
		t2.start();
		t1.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
