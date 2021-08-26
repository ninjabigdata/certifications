package section.commons;

import java.util.concurrent.TimeUnit;

public class LoopTaskG implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> STARTING #####");

		for (int i = 0; ; i++) {
			System.out.println("[" + currentThreadName + "] <" + taskId + ">Tick tick " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
			} catch (InterruptedException e) {
				System.out.println("##### [" + currentThreadName + "] <" + taskId + "> sleep interrupted. Cancelling #####");
				
				break;
			}
		}

		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> DONE #####");
	}

	public LoopTaskG() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskG" + instanceNumber;
	}
}
