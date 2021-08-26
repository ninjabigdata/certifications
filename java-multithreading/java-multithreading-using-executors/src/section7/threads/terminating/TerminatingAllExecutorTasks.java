package section7.threads.terminating;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import section.commons.CalculationTaskB;
import section.commons.CalculationTaskC;
import section.commons.FactorialTaskB;
import section.commons.LoopTaskA;
import section.commons.LoopTaskF;
import section.commons.NamedThreadFactory;
import section.commons.TaskResult;

public class TerminatingAllExecutorTasks {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ExecutorService service = Executors.newCachedThreadPool(new NamedThreadFactory());

		LoopTaskA t1 = new LoopTaskA();
		LoopTaskF t2 = new LoopTaskF();
		FactorialTaskB t3 = new FactorialTaskB(30, 500);
		CalculationTaskC t4 = new CalculationTaskC();
		CalculationTaskB t5 = new CalculationTaskB(2, 3, 9000);

		service.execute(t1);
		service.execute(t2);
		Future<Long> f3 = service.submit(t3);
		Future<Long> f4 = service.submit(t4);
		Future<TaskResult<String, Integer>> f5 = service.submit(t5);

		TimeUnit.MILLISECONDS.sleep(1000);

		service.shutdownNow();

		System.out.println("[" + currentThreadName + "] ALL THREADS TERMINATED = "
				+ service.awaitTermination(5000, TimeUnit.MILLISECONDS));

		System.out.println("[" + currentThreadName + "] 'FactorialTaskB' Result = " + f3.get());

		System.out.println("[" + currentThreadName + "] 'CalculationTaskC' Result = " + f4.get());

		try {
			System.out.println("[" + currentThreadName + "] 'CalculationTaskB' Result = " + f5.get().toString());
		} catch (ExecutionException exception) {
			System.out.println("[" + currentThreadName + "] 'CalculationTaskB' Got exception");
			exception.getCause().printStackTrace();
		}

		System.out.println("[" + currentThreadName + "] Main thread ends");
	}

}
