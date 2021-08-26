package section8.threads.handlingExceptions;

import section.commons.ExceptionLeakingTask;
import section.commons.ThreadExceptionHandler;

public class HandlingUncaughtExceptionsDefaultAndOverrides {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts");
		
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("DEFAULT_HANDLER"));
		
		Thread t1 = new Thread(new ExceptionLeakingTask(), "MyThread-1");
		
		Thread t2 = new Thread(new ExceptionLeakingTask(), "MyThread-2");
		t2.setUncaughtExceptionHandler(new ThreadExceptionHandler("CUSTOM_HANDLER_1"));
		
		Thread t3 = new Thread(new ExceptionLeakingTask(), "MyThread-3");
		
		Thread t4 = new Thread(new ExceptionLeakingTask(), "MyThread-4");
		t4.setUncaughtExceptionHandler(new ThreadExceptionHandler("CUSTOM_HANDLER_2"));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		System.out.println("[" + currentThreadName + "] Main thread ends");
	}

}
