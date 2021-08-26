package section7.threads.terminating;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import section.commons.FactorialTaskB;
import section.commons.LoopTaskA;
import section.commons.LoopTaskG;
import section.commons.NamedThreadFactory;

public class TerminatingBlockedExecutorTasks {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ExecutorService service = Executors.newCachedThreadPool(new NamedThreadFactory());

		LoopTaskA t1 = new LoopTaskA();
		LoopTaskG t2 = new LoopTaskG();
		FactorialTaskB t3 = new FactorialTaskB(30, 500);

		Future<?> f1 = service.submit(t1);
		Future<?> f2 = service.submit(t2);
		Future<?> f3 = service.submit(t3);

		service.shutdown();

		TimeUnit.MILLISECONDS.sleep(2000);
		System.out.println("[" + currentThreadName + "] Invoking cancel() on all tasks");
		f1.cancel(true);
		f2.cancel(true);
		f3.cancel(true);

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
