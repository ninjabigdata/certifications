package section1;

import java.util.concurrent.TimeUnit;

public class FourthWay {

	public static void main(String[] args) {
		System.out.println("Main thread starts");
		
		new Thread(new FourthTask()).start();
		
		Thread thread = new Thread(new FourthTask());
		thread.start();

		System.out.println("Main thread ends");
	}

}

class FourthTask implements Runnable {
	
	private static int count = 0;
	private int id;
	
	@Override
	public void run() {
		for (int i=0; i<10; i++) {
			System.out.println("<" + id + ">Tick tick " + i);
			
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public FourthTask() {
		this.id = ++count;
	}
}
