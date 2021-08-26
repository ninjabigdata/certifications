package section.commons;

import java.util.concurrent.TimeUnit;

public class LoopTaskD implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	private long sleepTime;

	@Override
	public void run() {
		boolean isRunningDaemonThread = Thread.currentThread().isDaemon();
		String threadType = isRunningDaemonThread ? "DAEMON" : "USER";

		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + ", " + threadType + "] <" + taskId + "> STARTING #####");

		for (int i = 0; i < 10; i++) {
			System.out.println("[" + currentThreadName + ", " + threadType + "] <" + taskId + ">Tick tick " + i);

			try {
				TimeUnit.MILLISECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("##### [" + currentThreadName + ", " + threadType + "] <" + taskId + "> DONE #####");
	}

	public LoopTaskD(long sleepTime) {
		this.sleepTime = sleepTime;
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskD" + instanceNumber;
	}
}
