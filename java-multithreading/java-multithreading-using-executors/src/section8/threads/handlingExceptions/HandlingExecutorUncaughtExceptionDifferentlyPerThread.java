package section8.threads.handlingExceptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import section.commons.ExceptionLeakingTask;
import section.commons.ThreadExceptionHandler;
import section.commons.ThreadFactoryWithExceptionHandler;

public class HandlingExecutorUncaughtExceptionDifferentlyPerThread {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts");
		
		ExecutorService service = Executors.newCachedThreadPool(new ThreadFactoryWithExceptionHandler());
		service.execute(new ExceptionLeakingTask());
		service.execute(new ExceptionLeakingTask());
		service.execute(new ExceptionLeakingTask());
		service.execute(new ExceptionLeakingTask());
		
		service.shutdown();

		System.out.println("[" + currentThreadName + "] Main thread ends");
	}

}
