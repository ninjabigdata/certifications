package section8.threads.handlingExceptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import section.commons.ExceptionLeakingTask;
import section.commons.ThreadExceptionHandler;
import section.commons.ThreadFactoryWithExceptionHandlerAlternator;

public class HandlingExecutorUncaughtExceptionsDefaultAndOverride {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts");

		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("DEFAULT_HANDLER"));

		ExecutorService service = Executors.newCachedThreadPool(new ThreadFactoryWithExceptionHandlerAlternator());
		service.execute(new ExceptionLeakingTask());
		service.execute(new ExceptionLeakingTask());
		service.execute(new ExceptionLeakingTask());
		service.execute(new ExceptionLeakingTask());

		service.shutdown();

		System.out.println("[" + currentThreadName + "] Main thread ends");
	}

}
