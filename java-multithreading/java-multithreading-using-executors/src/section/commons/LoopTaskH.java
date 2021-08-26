package section.commons;

import java.util.concurrent.TimeUnit;

public class LoopTaskH implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	private boolean sleepInterrupted = false;

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> STARTING #####");

		for (int i = 0;; i++) {
			System.out.println("[" + currentThreadName + "] <" + taskId + ">Tick tick " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
			} catch (InterruptedException e) {
				System.out.println("##### [" + currentThreadName + "] <" + taskId
						+ "> sleep interrupted. Setting the flag. #####");

				sleepInterrupted = true;
			}

			doSomemoreWork();

			if (sleepInterrupted || Thread.interrupted()) {
				System.out.println(
						"##### [" + currentThreadName + "] <" + taskId + "> sleep interrupted. Cancelling. #####");
				break;
			}
		}

		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> DONE #####");
	}

	private void doSomemoreWork() {
		System.out
				.println("##### [" + Thread.currentThread().getName() + "] <" + taskId + "> DOING SOMEMORE WORK #####");
	}

	public LoopTaskH() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskH" + instanceNumber;
	}
}
