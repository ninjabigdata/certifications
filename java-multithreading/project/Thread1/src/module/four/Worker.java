package module.four;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The synchronized methods acquires lock on {@link Worker}. This causes other
 * threads to wait until the current thread releases the lock. Even-though the
 * two methods executes on different objects, it would be wiser to lock on
 * resource i.e., variable instead of class
 *
 */
public class Worker {

	Random random = new Random();
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();

	public synchronized void stageOne() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		list1.add(random.nextInt());
	}

	public synchronized void stageTwo() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		list2.add(random.nextInt());
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
