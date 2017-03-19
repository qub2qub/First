package Concurrency.ProdCons;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to solve ProducerVector ConsumerVector problem using wait and notify
 * method in Java. ProducerVector ConsumerVector is also a popular concurrency design pattern.
 *
 * @author Javin Paul
 */
public class ProducerConsumerVectorNotify {

	public static void main(String args[]) {
		Vector sharedQueue = new Vector();
		int size = 4;
		Thread prodThread = new Thread(new ProducerVector(sharedQueue, size), "ProducerVector");
		Thread consThread = new Thread(new ConsumerVector(sharedQueue, size), "ConsumerVector");
		prodThread.start();
		consThread.start();
	}
}

class ProducerVector implements Runnable {

	private final Vector sharedQueue;
	private final int SIZE;

	public ProducerVector(Vector sharedQueue, int size) {
		this.sharedQueue = sharedQueue;
		this.SIZE = size;
	}
	@Override
	public void run() {
		for (int i = 1; i < 8; i++) {
			System.out.println("+Produced: " + i);
			try {
				produce(i);
			} catch (InterruptedException ex) {
				Logger.getLogger(ProducerVector.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private void produce(int i) throws InterruptedException {
		//wait if queue is full
		synchronized (sharedQueue) {
			while (sharedQueue.size() == SIZE) {
				System.out.println("Queue is full " + Thread.currentThread().getName()
						+ " is waiting , size: " + sharedQueue.size());
				sharedQueue.wait();
			}
			//producing element and notify consumers
			sharedQueue.add(i);
			sharedQueue.notifyAll();
		} // synchronized
	}
}

class ConsumerVector implements Runnable {

	private final Vector sharedQueue;
	private final int SIZE;

	public ConsumerVector(Vector sharedQueue, int size) {
		this.sharedQueue = sharedQueue;
		this.SIZE = size;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Consumed: " + consume());
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				Logger.getLogger(ConsumerVector.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
	}

	private int consume() throws InterruptedException {
		//wait if queue is empty
		synchronized (sharedQueue) {
			while (sharedQueue.isEmpty()) {
				System.out.println("Queue is empty " + Thread.currentThread().getName()
						+ " is waiting , size: " + sharedQueue.size());

				sharedQueue.wait();
			}
			//Otherwise consume element and notify waiting producer
			sharedQueue.notifyAll();
			return (Integer) sharedQueue.remove(0);
		} // synchronized
	}
}