package section3.threads.returnvalues;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import section.commons.CalculationTaskA;
import section.commons.LoopTaskA;
import section.commons.NamedThreadFactory;

public class ReturningValueUsingExecutorsFirstWay {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());
		
		Future<Integer> result1 = executorService.submit(new CalculationTaskA(10, 2, 100));
		Future<Integer> result2 = executorService.submit(new CalculationTaskA(11, 3, 150));
		Future<Integer> result3 = executorService.submit(new CalculationTaskA(12, 4, 300));
		
		Future<?> result4 = executorService.submit(new LoopTaskA());
		
		Future<Double> result5 = executorService.submit(new LoopTaskA(), 999.888);
		
		executorService.shutdown();
		
		try {
			System.out.println("Result-1 " + result1.get());
			System.out.println("Result-2 " + result2.get());
			System.out.println("Result-3 " + result3.get());
			System.out.println("Result-4 " + result4.get());
			System.out.println("Result-5 " + result5.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
