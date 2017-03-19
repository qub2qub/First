package Concurrency.bank;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

	private AtomicInteger failCounter = new AtomicInteger(0);
	private Lock lock = new ReentrantLock();
	private int balance;

	public Account(int initialBalance) {
		super();
		this.balance = initialBalance;
	}

	public int getFailCounter() {
		return failCounter.intValue();
	}

	public int incFailCounter() {
		return failCounter.incrementAndGet();
	}

	public int withdraw(int amount) {
		return balance -= amount;
	}

	public int deposit(int amount) {
		return balance += amount;
	}

	public int getBalance() {
		return balance;
	}

	public Lock getLock() {
		return lock;
	}
	
	
	
}
