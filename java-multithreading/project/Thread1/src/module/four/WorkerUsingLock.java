package module.four;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * To solve the issue specified in {@link Worker} class, synchronize the block
 * of code which is accessing the shared resource using lock. This ensures only
 * the resource used within the synchronized block will be locked.
 *
 */
public class WorkerUsingLock {

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private Random random = new Random();
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();

	public void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			list1.add(random.nextInt());
		}
	}

	public void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			list2.add(random.nextInt());
		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void main() {
		System.out.println("Starting");

		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				process();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				process();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("Time Taken: " + (end - start));

		System.out.println("List 1 Size: " + list1.size() + "; list 2 size: " + list2.size());

	}

}
