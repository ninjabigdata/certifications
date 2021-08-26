package section3.threads.returnvalues;

import section.commons.ValueReturningTaskA;

public class ReturningValueFirstWay {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ValueReturningTaskA task1 = new ValueReturningTaskA(2, 3, 100);
		Thread t1 = new Thread(task1, "Thread-1");

		ValueReturningTaskA task2 = new ValueReturningTaskA(3, 4, 1000);
		Thread t2 = new Thread(task2, "Thread-1");

		ValueReturningTaskA task3 = new ValueReturningTaskA(4, 5, 2000);
		Thread t3 = new Thread(task3, "Thread-1");
		
		t1.start();
		t2.start();
		t3.start();
		
		System.out.println("Result-1 = " + task1.getSum());
		System.out.println("Result-2 = " + task2.getSum());
		System.out.println("Result-3 = " + task3.getSum());

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
