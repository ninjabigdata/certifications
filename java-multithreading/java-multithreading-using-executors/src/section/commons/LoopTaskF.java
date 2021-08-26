package section.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LoopTaskF implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> STARTING #####");

		for (int i = 0;; i++) {
			System.out.println("[" + currentThreadName + "] <" + taskId + ">Tick tick " + i);

			doSomeWork();

			if (Thread.interrupted()) {
				System.out.println("##### [" + currentThreadName + "] <" + taskId + "> Interrupted. Cancelling #####");

				break;
			}

		}

		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> retrieving 'INTERRUPTED'status again "
				+ Thread.interrupted() + " #####");

		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> DONE #####");
	}

	public void doSomeWork() {
		for (int i = 0; i < 2; i++) {
			Collections.sort(generateDataset());
		}
	}

	private List<Integer> generateDataset() {
		List<Integer> numbers = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 100000; i++) {
			numbers.add(random.nextInt(100000));
		}

		return numbers;
	}

	public LoopTaskF() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskF" + instanceNumber;
	}
}
