package section3.threads.returnvalues;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import section.commons.CalculationTaskB;
import section.commons.LoopTaskA;
import section.commons.NamedThreadFactory;
import section.commons.TaskResult;

public class ReturningValueUsingExecutorsSecondWay {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "]Main thread starts");

		ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());

		CompletionService<TaskResult<String, Integer>> tasks = new ExecutorCompletionService<>(executorService);

		tasks.submit(new CalculationTaskB(10, 2, 1000));
		tasks.submit(new CalculationTaskB(11, 3, 1500));
		tasks.submit(new CalculationTaskB(12, 4, 300));

//		Future<?> result4 = executorService.submit(new LoopTaskA());

		tasks.submit(new LoopTaskA(), new TaskResult<>("LoopTaskA-1", 999));

		executorService.shutdown();

		for (int i = 0; i < 4; i++) {
			try {
				System.out.println("Result = " + tasks.take().get().toString());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		System.out.println("[" + currentThreadName + "]Main thread ends");
	}

}
