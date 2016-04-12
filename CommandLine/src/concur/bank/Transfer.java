package concur.bank;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Transfer implements Callable<Boolean> {
	
	private long WAIT = 1500;
	
	Account from;
	Account to;
	int amount;
	String name;

	public Transfer(Account from, Account to, int amount, String name) {
		super();
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.name = name;
	}

	@Override
	public Boolean call() throws Exception {

		try {
			if (from.getLock().tryLock(WAIT, TimeUnit.MILLISECONDS)) {
				System.out.println(name+" FROM locked");
				System.out.println(name+" 1 from="+from.getBalance());
				System.out.println(name+" 1 amount = "+amount);
				if (from.getBalance() < amount) {
					System.out.println(name+" Transfer Failed !! NO MONEY");
					from.incFailCounter();
					throw new NoMoneyException();
				}
				Thread.sleep(1000);
				//------------------- 1 ------------------
				try {
					if (to.getLock().tryLock(WAIT, TimeUnit.MILLISECONDS)) {
						System.out.println(name+" TO locked");
						System.out.println(name+" 1 to="+to.getBalance());
						//------------------- 2 ------------------
						try {
							from.withdraw(amount);
							to.deposit(amount);
//							int sleep = (int)Math.random()*1000;
							int sleep = new Random().nextInt(1000);
							System.out.println("sleep="+sleep);
							Thread.sleep(sleep);
							System.out.println(name + " Transfer Successed !! " + amount);
							return true;
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
		return false;
	}

}
