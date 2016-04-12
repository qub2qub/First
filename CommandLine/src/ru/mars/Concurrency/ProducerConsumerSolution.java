package ru.mars.Concurrency;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to solve ProducerWN ConsumerWN problem using wait and notify
 * method in Java. ProducerWN ConsumerWN is also a popular concurrency design pattern.
 *
 * @author Javin Paul
 */
public class ProducerConsumerSolution {

	public static void main(String args[]) {
		Vector sharedQueue = new Vector();
		int size = 4;
		Thread prodThread = new Thread(new ProducerWN(sharedQueue, size), "ProducerWN");
		Thread consThread = new Thread(new ConsumerWN(sharedQueue, size), "ConsumerWN");
		prodThread.start();
		consThread.start();
	}
}

class ProducerWN implements Runnable {

	private final Vector sharedQueue;
	private final int SIZE;

	public ProducerWN(Vector sharedQueue, int size) {
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
				Logger.getLogger(ProducerWN.class.getName()).log(Level.SEVERE, null, ex);
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
		}
	}
}

class ConsumerWN implements Runnable {

	private final Vector sharedQueue;
	private final int SIZE;

	public ConsumerWN(Vector sharedQueue, int size) {
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
				Logger.getLogger(ConsumerWN.class.getName()).log(Level.SEVERE, null, ex);
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
		}
	}
}