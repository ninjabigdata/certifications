package section8.threads.handlingExceptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import section.commons.ExceptionLeakingTask;
import section.commons.ThreadExceptionHandler;

public class HandlingExecutorUncaughtExceptionForEveryThread {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts");
		
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("DEFAULT_HANDLER"));
		
		ExecutorService service1 = Executors.newCachedThreadPool();
		service1.execute(new ExceptionLeakingTask());
		service1.execute(new ExceptionLeakingTask());
		service1.execute(new ExceptionLeakingTask());
		
		ExecutorService service2 = Executors.newCachedThreadPool();
		service2.execute(new ExceptionLeakingTask());
		service2.execute(new ExceptionLeakingTask());
		service2.execute(new ExceptionLeakingTask());
		
		service1.shutdown();
		service2.shutdown();

		System.out.println("[" + currentThreadName + "] Main thread ends");
	}

}
