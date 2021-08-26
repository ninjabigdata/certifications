package section.commons;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadExceptionHandler implements UncaughtExceptionHandler {

	private String handlerId;

	public ThreadExceptionHandler(String handlerId) {
		this.handlerId = handlerId;
	}

	public ThreadExceptionHandler() {
		// Do Nothing
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(this + " caught exception in Thread - '" + t.getName() + "' => " + e);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + this.hashCode()
				+ (handlerId == null || "".equals(handlerId) ? "" : "'" + handlerId + "'");
	}

}
