package module.twelve;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection connection = new Connection();
	private int connections = 0;
	private Semaphore semaphore = new Semaphore(10);

	private Connection() {

	}

	public static Connection getInstance() {
		return connection;
	}

	public void connect() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			doConnect();
		} finally {
			semaphore.release();
		}
	}

	private void doConnect() {
		synchronized (this) {
			connections++;
			System.out.println("Current connections is " + connections);
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (this) {
			connections--;
		}
	}

}
