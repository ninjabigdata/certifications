package section2.thread.naming;

import java.util.concurrent.TimeUnit;

import section.commons.LoopTaskC;

public class NamingThreadsSecondWay {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		new Thread(new LoopTaskC(), "MyThread-1").start();
		Thread thread = new Thread(new LoopTaskC());
		// thread.setName("MyThread-2");
		thread.start();

		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		thread.setName("MyThread-2");

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
