package module.three;

/**
 * Demonstrates the usage of synchronized method
 *
 */
public class SynchronizedUsage {

	private int count = 0;

	public static void main(String[] args) {
		SynchronizedUsage synchronizedUsage = new SynchronizedUsage();
		synchronizedUsage.doWork();
	}

	private synchronized void update() {
		count++;
	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					update();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					update();
				}
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

		System.out.println("Count is " + count);
	}

}

/**
 * This class demonstrates the usage of join()
 * 
 * The value of count variable printed may not be 20000 as expected. Since the
 * thread may not have the latest value of count which could have updated by
 * another thread before another thread updates the value.
 * 
 * To overcome this issue use synchronized block or method to update the count
 * variable. This ensures the shared resource will be accessed by only one
 * thread at any given time.
 *
 */
class ThreadJoinUsage {

	private int count = 0;

	public static void main(String[] args) {
		ThreadJoinUsage threadJoinUsage = new ThreadJoinUsage();
		threadJoinUsage.doWork();
	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
				}
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

		System.out.println("Count is " + count);
	}

}

/**
 * The variable count will be incremented by both the threads. But Sysout
 * statement prints initial value of the count. As that sysout will be executed
 * on main thread.
 *
 * Invoke join() on both the threads which causes the main thread wait to till
 * both threads complete the task.
 */
class WithouSynchronizedKeyword {

	private int count = 0;

	public static void main(String[] args) {
		WithouSynchronizedKeyword withouSynchronizedKeyword = new WithouSynchronizedKeyword();
		withouSynchronizedKeyword.doWork();
	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
				}
			}
		});

		t1.start();
		t2.start();

		System.out.println("Count is " + count);
	}

}
