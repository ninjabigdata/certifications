package section4.threads.daemonThreads;

import section.commons.LoopTaskD;

public class DaemonThreads {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");
		
		Thread t1 = new Thread(new LoopTaskD(500), "Thread-1");
		t1.setDaemon(true);
		
		Thread t2 = new Thread(new LoopTaskD(750), "Thread-2");
//		t2.setDaemon(true);
		
		t1.start();
		t2.start();

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
