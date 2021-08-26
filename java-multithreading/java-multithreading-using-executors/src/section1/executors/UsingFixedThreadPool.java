package section1.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import section.commons.LoopTaskA;

public class UsingFixedThreadPool {

	public static void main(String[] args) {
		System.out.println("Main thread starts");

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		executorService.execute(new LoopTaskA());
		executorService.execute(new LoopTaskA());
		executorService.execute(new LoopTaskA());
		executorService.execute(new LoopTaskA());
		
		executorService.shutdown();

		executorService.execute(new LoopTaskA());

		System.out.println("Main thread ends");
	}

}
