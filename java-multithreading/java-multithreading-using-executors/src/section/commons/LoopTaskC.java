package section.commons;

import java.util.concurrent.TimeUnit;

public class LoopTaskC implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	@Override
	public void run() {
		// String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + Thread.currentThread().getName() + "] <" + taskId + "> STARTING #####");

		for (int i = 0; i < 10; i++) {
			System.out.println("[" + Thread.currentThread().getName() + "] <" + taskId + ">Tick tick " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("##### [" + Thread.currentThread().getName() + "] <" + taskId + "> DONE #####");
	}

	public LoopTaskC() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskC" + instanceNumber;
	}
}
