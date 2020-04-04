package module.eleven;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account account1 = new Account();
	private Account account2 = new Account();
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
		while (true) {
			// Acquire locks
			boolean getFirstLock = false;
			boolean getSecondLock = false;

			try {
				getFirstLock = firstLock.tryLock();
				getSecondLock = secondLock.tryLock();
			} finally {
				if (getFirstLock && getSecondLock) {
					return;
				}

				if (getFirstLock) {
					firstLock.unlock();
				}

				if (getSecondLock) {
					secondLock.unlock();
				}
			}

			// Locks not acquired
			Thread.sleep(1);
		}
	}

	public void firstThread() throws InterruptedException {

		Random random = new Random();

		for (int i = 0; i < 10000; i++) {
			acquireLocks(lock1, lock2);
			try {
				Account.transfer(account1, account2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}

	}

	public void secondThread() throws InterruptedException {

		Random random = new Random();

		for (int i = 0; i < 10000; i++) {
			acquireLocks(lock2, lock1);
			try {
				Account.transfer(account2, account1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void finished() {
		System.out.println("Account 1 balance is " + account1.getBalance());
		System.out.println("Account 2 balance is " + account2.getBalance());
		System.out.println("Total balance is " + (account1.getBalance() + account2.getBalance()));
	}

}
