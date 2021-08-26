package section9.threads.joining;

import section.commons.ValueReturningTaskC;

public class ReturningValueThirdWay {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ValueReturningTaskC task1 = new ValueReturningTaskC(2, 3, 100);
		Thread t1 = new Thread(task1, "Thread-1");

		ValueReturningTaskC task2 = new ValueReturningTaskC(3, 4, 1000);
		Thread t2 = new Thread(task2, "Thread-2");

		ValueReturningTaskC task3 = new ValueReturningTaskC(4, 5, 2000);
		Thread t3 = new Thread(task3, "Thread-3");

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		System.out.println("Result-1 = " + task1.getSum());

		t2.join();
		System.out.println("Result-2 = " + task2.getSum());

		t3.join();
		System.out.println("Result-3 = " + task3.getSum());

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
