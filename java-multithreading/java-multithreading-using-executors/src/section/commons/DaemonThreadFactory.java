package section.commons;

public class DaemonThreadFactory extends NamedThreadFactory {

	private static int count = 0;

	@Override
	public Thread newThread(Runnable r) {
		Thread t = super.newThread(r);

		if (++count % 2 == 0) {
			t.setDaemon(true);
		}

		return t;
	}

}
