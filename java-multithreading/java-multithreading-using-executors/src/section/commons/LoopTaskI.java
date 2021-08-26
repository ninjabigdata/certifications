package section.commons;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LoopTaskI implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	private CountDownLatch countDownLatch;

	@Override
	public void run() {
		boolean isRunningDaemonThread = Thread.currentThread().isDaemon();
		String threadType = isRunningDaemonThread ? "DAEMON" : "USER";

		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + ", " + threadType + "] <" + taskId + "> STARTING #####");

		for (int i = 0; i < 10; i++) {
			System.out.println("[" + currentThreadName + ", " + threadType + "] <" + taskId + ">Tick tick " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("##### [" + currentThreadName + ", " + threadType + "] <" + taskId + "> DONE #####");

		if (countDownLatch != null) {
			countDownLatch.countDown();

			System.out.println("##### [" + currentThreadName + ", " + threadType + "] <" + taskId + "> LATCH Count = "
					+ countDownLatch.getCount());
		}
	}

	public LoopTaskI(CountDownLatch latch) {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskI" + instanceNumber;
		this.countDownLatch = latch;
	}
}
