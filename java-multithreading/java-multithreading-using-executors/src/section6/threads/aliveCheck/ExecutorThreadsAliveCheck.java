package section6.threads.aliveCheck;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import section.commons.CalculationTaskA;
import section.commons.LoopTaskC;
import section.commons.NamedThreadFactory;

public class ExecutorThreadsAliveCheck {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ExecutorService service = Executors.newCachedThreadPool(new NamedThreadFactory());

		Future<?> f1 = service.submit(new LoopTaskC());
		Future<Integer> f2 = service.submit(new CalculationTaskA(1, 3, 700));

		FutureTask<?> ft1 = new FutureTask<>(new LoopTaskC(), null);
		FutureTask<Integer> ft2 = new FutureTask<>(new LoopTaskC(), 999);
		FutureTask<Integer> ft3 = new FutureTask<>(new CalculationTaskA(2, 3, 800));

		service.submit(ft1);
		service.submit(ft2);
		service.submit(ft3);

		service.shutdown();

		for (int i = 0; i <= 5; i++) {
			TimeUnit.MILLISECONDS.sleep(600);

			System.out.println("[" + currentThreadName + "] ITR-" + i + " is 'LoopTaskC-1' done" + f1.isDone());
			System.out.println("[" + currentThreadName + "] ITR-" + i + " is 'CalcTaskA-1' done" + f2.isDone());

			System.out.println("[" + currentThreadName + "] ITR-" + i + " is 'LoopTaskC-2' done" + ft1.isDone());
			System.out.println("[" + currentThreadName + "] ITR-" + i + " is 'LoopTaskC-3' done" + ft2.isDone());
			System.out.println("[" + currentThreadName + "] ITR-" + i + " is 'CalcTaskA-2' done" + ft3.isDone());
		}

		System.out.println("\n$$$$$[" + currentThreadName + "] RETRIEVING RESULTS NOW ..... $$$$$");

		System.out.println("\n$$$$$[" + currentThreadName + "] 'LoopTaskC-1' result is " + f1.get());
		System.out.println("\n$$$$$[" + currentThreadName + "] 'CalcTaskA-1' result is " + f2.get());

		System.out.println("\n$$$$$[" + currentThreadName + "] 'LoopTaskC-2' result is " + ft1.get());
		System.out.println("\n$$$$$[" + currentThreadName + "] 'LoopTaskC-3' result is " + ft2.get());
		System.out.println("\n$$$$$[" + currentThreadName + "] 'CalcTaskA-2' result is " + ft3.get());

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
