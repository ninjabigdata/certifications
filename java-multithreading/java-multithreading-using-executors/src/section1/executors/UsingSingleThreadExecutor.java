package section1.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import section.commons.LoopTaskA;

public class UsingSingleThreadExecutor {

	public static void main(String[] args) {
		System.out.println("Main thread starts");

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		executorService.execute(new LoopTaskA());
		executorService.execute(new LoopTaskA());
		executorService.execute(new LoopTaskA());
		executorService.execute(new LoopTaskA());
		
		executorService.shutdown();

		System.out.println("Main thread ends");
	}

}
