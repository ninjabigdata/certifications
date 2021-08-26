package section.commons;

import java.util.concurrent.TimeUnit;

public class LoopTaskB implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	@Override
	public void run() {
		Thread.currentThread().setName("Amazing-Thread-" + instanceNumber);
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> STARTING #####");

		for (int i = 0; i < 10; i++) {
			System.out.println("[" + currentThreadName + "] <" + taskId + ">Tick tick " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> DONE #####");
	}

	public LoopTaskB() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskB" + instanceNumber;
	}
}
