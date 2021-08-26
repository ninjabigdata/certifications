package section6.threads.aliveCheck;

import java.util.concurrent.TimeUnit;

import section.commons.LoopTaskC;

public class ThreadsAliveCheck {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		Thread t1 = new Thread(new LoopTaskC(), "MyThread-1");
		Thread t2 = new Thread(new LoopTaskC(), "MyThread-2");

		boolean isT1Alive = t1.isAlive();
		boolean isT2Alive = t2.isAlive();

		System.out.println("[" + currentThreadName + "] BEFORE STARTING '" + t1.getName() + "' alive = " + isT1Alive);
		System.out.println("[" + currentThreadName + "] BEFORE STARTING '" + t2.getName() + "' alive = " + isT2Alive);

		t1.start();
		t2.start();

		while (true) {
			TimeUnit.MILLISECONDS.sleep(600);

			isT1Alive = t1.isAlive();
			isT2Alive = t2.isAlive();

			System.out
					.println("[" + currentThreadName + "] BEFORE STARTING '" + t1.getName() + "' alive = " + isT1Alive);
			System.out
					.println("[" + currentThreadName + "] BEFORE STARTING '" + t2.getName() + "' alive = " + isT2Alive);
			
			if (!isT1Alive && !isT2Alive) {
				break;
			}
		}

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
