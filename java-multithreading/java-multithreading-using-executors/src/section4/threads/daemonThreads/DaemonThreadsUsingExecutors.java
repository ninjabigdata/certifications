package section4.threads.daemonThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import section.commons.DaemonThreadFactory;
import section.commons.LoopTaskD;

public class DaemonThreadsUsingExecutors {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ExecutorService service = Executors.newCachedThreadPool(new DaemonThreadFactory());

		service.execute(new LoopTaskD(100));
		service.execute(new LoopTaskD(200));
		service.execute(new LoopTaskD(300));
		service.execute(new LoopTaskD(400));

		service.shutdown();

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
