package section10.threads.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import section.commons.ScheduledTaskA;
import section.commons.TimeUtils;

public class SchedulingTasksForOneTimeExecution {

	private static SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:SSS");

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts");

		Timer timer = new Timer("Timer-Thread", true);
		Date currentTime = new Date();
		Date scheduledTime = TimeUtils.getFutureTime(currentTime, 5000);

		System.out.println("[" + currentThreadName + "] Current Time is " + format.format(currentTime));

		timer.schedule(new ScheduledTaskA(8000), scheduledTime);

		System.out
				.println("[" + currentThreadName + "] Task-1 scheduled for RUNNING AT " + format.format(scheduledTime));

		// *******************************************************************************//
		long delayMillis = 10000;
		ScheduledTaskA task2 = new ScheduledTaskA(4000);

		timer.schedule(task2, delayMillis);

		System.out.println("[" + currentThreadName + "] Task-2 scheduled to run " + delayMillis / 1000
				+ " seconds INITIAL-DELAY after current-time at "
				+ format.format(new Date(task2.scheduledExecutionTime())));

		// *******************************************************************************//
		long delayMillis2 = 12000;
		ScheduledTaskA task3 = new ScheduledTaskA(0);

		timer.schedule(task3, delayMillis2);

		System.out.println("[" + currentThreadName + "] Task-3 scheduled to run " + delayMillis2 / 1000
				+ " seconds INITIAL-DELAY after current-time at "
				+ format.format(new Date(task3.scheduledExecutionTime())));

		// *******************************************************************************//
		Date scheduledTask2 = TimeUtils.getFutureTime(currentTime, 30000);
		ScheduledTaskA task4 = new ScheduledTaskA(0);

		timer.schedule(task4, scheduledTask2);

		System.out.println("[" + currentThreadName + "] Task-4 scheduled for RUNNING AT "
				+ format.format(new Date(task4.scheduledExecutionTime())));

		task4.cancel();

		TimeUnit.MILLISECONDS.sleep(32000);

		System.out.println("[" + currentThreadName + "] CANCELLING THE TIMER");

		timer.cancel();

		System.out.println("[" + currentThreadName + "] Main thread ends");
	}

}
