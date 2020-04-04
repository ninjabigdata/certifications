package module.four;

public class LockDemo {

	public static void main(String[] args) {
		new Worker().main();
		new WorkerUsingLock().main();
	}

}
