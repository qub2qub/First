package ru.mars.Concurrency;

public class DeadLockTest {gitk
	static class ThreadOne implements Runnable {
		public void run() {
			synchronized (Integer.class) {
				System.out.println(Thread.currentThread().getName() 
						+ " - a.One Lock Integer.class");
				synchronized (String.class) {
					System.out.println(Thread.currentThread().getName() 
							+ " - b.One Lock String.class");
				}
			}
		}
	}

	static class ThreadTwo implements Runnable {
		public void run() {
			synchronized (String.class) {
				System.out.println(Thread.currentThread().getName() 
						+ " - a.Two Lock String.class");
				synchronized (Integer.class) {
					System.out.println(Thread.currentThread().getName() 
							+ " - b.Two Lock Integer.class");
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		new Thread(new ThreadOne(), "ThreadOne").start();
//		new Thread(new ThreadTwo(), "ThreadTwo").start();
		for (int i = 1; i <= 3; i++) {
			new Thread(new ThreadOne(), "One_"+i).start();
			new Thread(new ThreadTwo(), "Two_"+i).start();
		}
	}
}
