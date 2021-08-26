package section2.thread.naming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import section.commons.LoopTaskC;
import section.commons.NamedThreadFactory;

public class NamingExecutorThreads {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());
		
		executorService.execute(new LoopTaskC());
		executorService.execute(new LoopTaskC());
		executorService.execute(new LoopTaskC());
	
		executorService.shutdown();

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
