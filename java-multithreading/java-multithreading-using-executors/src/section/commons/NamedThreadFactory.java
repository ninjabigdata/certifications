package section.commons;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
	
	private static int count = 0;
	private static String name = "MyThread-";

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, name + ++count);
		return thread;
	}

}
