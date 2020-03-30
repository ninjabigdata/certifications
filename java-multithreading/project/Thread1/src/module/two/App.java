package module.two;

import java.util.Scanner;

/**
 * The thread object created for this {@link Processor} class will have its own
 * copy of the running attribute. So when two objects are created, each one has
 * its copy of running attribute. So calling shutdown() in one thread will not
 * stop execution of another thread.
 * 
 * On optimizing the code, the condition for running can be removed since in
 * run() the value of running is never changed. So the thread would not stopped
 * on changing the value of running.
 *
 */
class Processor extends Thread {
	private boolean running = true;

	@Override
	public void run() {
		while (running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void shutdown() {
		running = false;
	}
}

/**
 * To overcome the problem defined in the {@link Processor} class, the volatile
 * keyword can be used.
 *
 * The while condition will always be evaluated. The optimization mentioned in
 * {@link Processor} class will not be done.
 *
 */
class ProccesorUsingVolatile extends Thread {
	private volatile boolean running = true;

	@Override
	public void run() {
		while (running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void shutdown() {
		running = false;
	}
}

public class App {

	public static void main(String[] args) {
		Processor processor = new Processor();
		processor.start();

		System.out.println("Press enter key to stop...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();

		processor.shutdown();
	}

}
