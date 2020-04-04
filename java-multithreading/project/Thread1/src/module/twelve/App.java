package module.twelve;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Semaphore semaphore = new Semaphore(1);

		semaphore.acquire();
		semaphore.release();

		System.out.println("Available permits is " + semaphore.availablePermits());

		Connection.getInstance().connect();

		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 200; i++) {
			executorService.submit(() -> {
				Connection.getInstance().connect();
			});
		}

		executorService.shutdown();

		executorService.awaitTermination(1, TimeUnit.DAYS);
	}

}
