package section7.threads.terminating;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import section.commons.FactorialTaskA;
import section.commons.LoopTaskE;
import section.commons.NamedThreadFactory;

public class TerminatingExecutorsTasksFirstWay {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ExecutorService service = Executors.newCachedThreadPool(new NamedThreadFactory());

		LoopTaskE t1 = new LoopTaskE();
		FactorialTaskA t2 = new FactorialTaskA(30, 1000);

		service.execute(t1);
		service.submit(t2);

		TimeUnit.MILLISECONDS.sleep(3000);

		t1.cancel();
		t2.cancel();

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
