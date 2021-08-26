package section1;

import java.util.concurrent.TimeUnit;

public class FirstWay {

	public static void main(String[] args) {
		System.out.println("Main thread starts");
		
		new FirstTask();
		@SuppressWarnings("unused")
		Thread t1 = new FirstTask();

		System.out.println("Main thread ends");
	}

}

class FirstTask extends Thread {
	
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

	public FirstTask() {
		this.id = ++count;
		this.start();
	}
}
