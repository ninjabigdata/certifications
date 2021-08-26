package section2.thread.naming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import section.commons.LoopTaskC;
import section.commons.NamedThreadFactory;

public class UsingCachedThreadPoolPart2 {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());
		
		executorService.execute(new LoopTaskC());
		executorService.execute(new LoopTaskC());
		executorService.execute(new LoopTaskC());
		
		TimeUnit.SECONDS.sleep(15);

		executorService.execute(new LoopTaskC());
		executorService.execute(new LoopTaskC());
		executorService.execute(new LoopTaskC());
		executorService.execute(new LoopTaskC());
		executorService.execute(new LoopTaskC());
	
		executorService.shutdown();

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
