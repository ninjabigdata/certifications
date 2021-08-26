package section9.threads.joining;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import section.commons.LoopTaskI;
import section.commons.NamedThreadFactory;

public class JoiningExecutorThreads {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts");

		ExecutorService service = Executors.newCachedThreadPool(new NamedThreadFactory());

		CountDownLatch countDownLatch = new CountDownLatch(2);

		service.execute(new LoopTaskI(null));
		service.execute(new LoopTaskI(countDownLatch));
		service.execute(new LoopTaskI(countDownLatch));
		service.execute(new LoopTaskI(null));

		service.shutdown();

		try {
			countDownLatch.await();

			System.out.println("[" + currentThreadName + "] Got the signal to continue");
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}

		System.out.println("[" + currentThreadName + "] Main thread ends");
	}

}
