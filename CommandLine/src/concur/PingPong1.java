package concur;

public class PingPong1 {

	static Object lock = new Object();
	
	public PingPong1() { }
	
	public static void main(String[] args) {
		final Object lock2 = new Object();
		new Thread(new Runnable() {
			int c = 0;
			@Override
			public void run() {
				while (true) {
					synchronized (lock2) {
						System.out.println("PING-"+c++);
						try {
//							Thread.sleep(300);
							lock.notify();
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			int c = 0;
			@Override
			public void run() {
				while (true) {
					synchronized (lock2) {
						System.out.println("Pong-"+c++);
						try {
//							Thread.sleep(200);
							lock.notify();
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}
		}).start();
		
//		new Thread(new Runnable() {
//			int c = 0;
//			@Override
//			public void run() {
//				while (true) {
//					synchronized (lock) {
//						System.out.println("NLO-"+c++);
//						try {
//							lock.notify();
//							lock.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//					
//				}
//			}
//		}).start();
	}
}
