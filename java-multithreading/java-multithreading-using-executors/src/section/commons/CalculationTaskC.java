package section.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class CalculationTaskC implements Callable<Long> {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	private final int dataSize = 100000;

	private boolean isThreadInterrupted = false;

	public CalculationTaskC() {
		this.instanceNumber = ++count;
		this.taskId = "CalcTaskC-" + instanceNumber;
	}

	@Override
	public Long call() throws Exception {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + "] <" + taskId + ">STARTING #####");

		long totalTimetakenInMillis = 0;

		for (int i = 0; i < 1000; i++) {
			System.out.println("[" + currentThreadName + "] <" + taskId + "> CURRENT RUNNING AVERAGE = "
					+ (i == 0 ? 0 : totalTimetakenInMillis / (2 * i)));

			long timeTakeninMillis = doSomework();
			totalTimetakenInMillis += timeTakeninMillis;

			if (Thread.interrupted()) {
				System.out.println("[" + currentThreadName + "] <" + taskId + "> Interrupted. Cancelling.");
				isThreadInterrupted = true;

				break;
			}
		}

		System.out.println("[" + currentThreadName + "] <" + taskId + "> Retrieving 'Interrupted' status again: "
				+ Thread.interrupted());

		System.out.println("##### [" + currentThreadName + "] <" + taskId + ">DONE #####");

		return isThreadInterrupted ? -1L : totalTimetakenInMillis / (2 * 1000);
	}

	private long doSomework() {
		long startTime = System.currentTimeMillis();

		for (int i = 0; i < 2; i++) {
			Collections.sort(generateDataset());
		}

		return System.currentTimeMillis() - startTime;
	}

	private List<Integer> generateDataset() {
		List<Integer> numbers = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < dataSize; i++) {
			numbers.add(random.nextInt(dataSize));
		}

		return numbers;
	}

}
