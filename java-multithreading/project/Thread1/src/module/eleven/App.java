package module.eleven;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Runner runner = new Runner();

		Thread t1 = new Thread(() -> {
			try {
				runner.firstThread();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				runner.secondThread();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		runner.finished();
	}

}
