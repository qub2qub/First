package Concurrency.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Operations2 {

	public static void main(String[] args) {
		ScheduledExecutorService sCheck = Executors.newSingleThreadScheduledExecutor();
		ExecutorService service = Executors.newFixedThreadPool(3);
		
		final Account account1a = new Account(1000);
		Account account2b = new Account(1000);
		
		sCheck.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("[[ A.fail = "+account1a.getFailCounter()+" ]]");
			}
		}, 1, 1, TimeUnit.SECONDS);
		
		List<Future<Boolean>> allTrans = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			allTrans.add(service.submit(
					new Transfer(account1a, account2b, new Random().nextInt(600), "a2b_"+i)));
//			fut.add(service.submit(
//				new Transfer(account2b, account1a, new Random().nextInt(500), "b2a_"+i)));
		}
		
		service.shutdown();
		try {
			service.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("4 A="+account1a.getBalance());
		System.out.println("4 B="+account2b.getBalance());
		System.out.println("4 A fail ="+account1a.getFailCounter());
		System.out.println("4 B fail ="+account2b.getFailCounter());
		
		for (Future<Boolean> future : allTrans) {
			try {
				System.out.println("* "+future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
}
