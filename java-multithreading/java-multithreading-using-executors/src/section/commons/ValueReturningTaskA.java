package section.commons;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskA implements Runnable {

	private int a;
	private int b;
	private long sleepTime;
	private int sum;
	private volatile boolean done = false;

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	public ValueReturningTaskA(int a, int b, long sleepTime) {
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;

		this.instanceNumber = ++count;
		this.taskId = "ValueReturningA-" + instanceNumber;
	}

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + "] <" + taskId + ">STARTING #####");

		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sum = a + b;

		System.out.println("##### [" + currentThreadName + "] <" + taskId + ">DONE #####");

		done = true;

		synchronized (this) {
			System.out.println("[" + currentThreadName + "] <" + taskId + ">NOTIFYING");
			this.notifyAll();
		}
	}

	/**
	 * @return the sum
	 */
	public int getSum() {
		if (!done) {
			synchronized (this) {
				try {
					System.out.println(
							"[" + Thread.currentThread().getName() + "] WAITING for result from " + taskId + "...");
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("[" + Thread.currentThread().getName() + "] WOKE-UP for result from " + taskId + "...");
		}
		return sum;
	}

}
