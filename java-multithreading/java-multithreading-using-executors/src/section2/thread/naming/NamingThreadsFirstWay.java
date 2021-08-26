package section2.thread.naming;

import section.commons.LoopTaskB;

public class NamingThreadsFirstWay {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		new Thread(new LoopTaskB()).start();
		Thread thread = new Thread(new LoopTaskB());
		thread.start();

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
