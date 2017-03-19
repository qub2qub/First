package Concurrency.bank;

import java.util.concurrent.TimeUnit;

public class Operations {

	public static long WAIT = 500;

	static void transfer(Account from, Account to, int amount, String name) 
			throws NoMoneyException {

		try {
			if (from.getLock().tryLock(WAIT, TimeUnit.MILLISECONDS)) {
				System.out.println(name+" FROM locked");
				System.out.println(name+" 1 from="+from.getBalance());
				System.out.println(name+" 1 amount = "+amount);
				if (from.getBalance() < amount) {
					System.out.println(name+" Transfer Failed !!");
					throw new NoMoneyException();
				}
				Thread.sleep(4000);
				//------------------- 1 ------------------
				try {
					if (to.getLock().tryLock(WAIT, TimeUnit.MILLISECONDS)) {
						System.out.println(name+" TO locked");
						System.out.println(name+" 1 to="+to.getBalance());
						//------------------- 2 ------------------
						try {
							from.withdraw(amount);
							to.deposit(amount);
							System.out.println("Transfer Successed !!");
					     } finally {
					         to.getLock().unlock();
					     }
						//------------------- 2 ------------------
					} else {
						System.out.println(name+" UNAble to lock TO");
						to.incFailCounter();
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} finally {
					from.getLock().unlock();
				}
				//------------------- 1 ------------------
			} else {
				System.out.println(name+" UNAble to lock FROM");
				from.incFailCounter();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}



		//		synchronized (from) {
		//			System.out.println(name+" Sync From");
		//			try {
		//				Thread.sleep(500);
		//				synchronized (to) {
		//					System.out.println(name+" Sync To");
		//					from.withdraw(amount);
		//					to.deposit(amount);
		//				}
		//			} catch (InterruptedException e) {
		//				e.printStackTrace();
		//			}
		//		}
//		System.out.println("Transfer Successed !!");


	}
	
	public static void createOperation(final Account from, final Account to, final int amount, final String name) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Operations.transfer(from, to, amount, name);
				} catch (NoMoneyException e) {
					e.printStackTrace();
				}
			}
		}, name).start();
	}

	//---------------------------------------------------------
	public static void main(String[] args) {
		final Account account1a = new Account(1000);
		final Account account2b = new Account(2000);
		
		createOperation(account1a, account2b, 1500, "Oper_0");
//		createOperation(account1a, account2b, 500, "Oper_1");
//		createOperation(account2b, account1a, 300, "Oper_2");
//		createOperation(account1a, account2b, 700, "Oper_3");
		

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("2 A="+account1a.getBalance());
		System.out.println("2 B="+account2b.getBalance());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("3 A="+account1a.getBalance());
		System.out.println("3 B="+account2b.getBalance());
		System.out.println("3 A fail ="+account1a.getFailCounter());
		System.out.println("3 B fail ="+account2b.getFailCounter());

	}
}

//Op1 1 from=1000
//Op1 1 to=2000
//Op1 1 amount = 500
//Op1 FROM locked
//Op2 1 from=2000
//Op2 1 to=1000
//Op2 1 amount = 300
//Op2 FROM locked
//2 A=1000
//2 B=2000
//Op1 UNAble to lock TO
//Op2 TO locked
//Transfer Successed !!
//3 A=1300
//3 B=1700
