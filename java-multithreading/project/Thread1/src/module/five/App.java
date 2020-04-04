package module.five;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

	private int id;

	public Processor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Starting of " + id);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("End of " + id);
	}

}

public class App {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 5; i++) {
			executorService.submit(new Processor(i));
		}

		executorService.shutdown();

		System.out.println("All the tasks submitted");

		try {
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("All tasks completed");
	}

}
