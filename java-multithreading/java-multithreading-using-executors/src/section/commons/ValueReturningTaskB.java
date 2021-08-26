package section.commons;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskB implements Runnable {

	private int a;
	private int b;
	private long sleepTime;
	private int sum;
	private ResultListener<Integer> listener;

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	public ValueReturningTaskB(int a, int b, long sleepTime, ResultListener<Integer> listener) {
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		this.listener = listener;

		this.instanceNumber = ++count;
		this.taskId = "ValueReturningB-" + instanceNumber;
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

		listener.notifyResult(sum);
	}

}
