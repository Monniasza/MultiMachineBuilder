/**
 * 
 */
package mmb.RUNTIME;

/**
 * @author oskar
 *
 */
public class TaskLoop {
	private final Thread thread;
	private boolean destroyOnFail = false;
	/**
	 * @return will thread be destroyed if it fails?
	 */
	public boolean isDestroyOnFail() {
		return destroyOnFail;
	}
	/**
	 * @param destroyOnFail should this task loop be destroyed if thread fails?
	 */
	public void setDestroyOnFail(boolean destroyOnFail) {
		this.destroyOnFail = destroyOnFail;
	}
	private int state = 0;
	private long next;
	/**
	 * @param task task to run
	 * @param period period in nanoseconds
	 */
	public TaskLoop(Runnable task, long period) {
		thread = new Thread(() -> {
			Thread thr = Thread.currentThread();
			while(true) {
				if(thr.isInterrupted()) {
					state = 2;
					return;
				}
				long currTime = System.nanoTime();
				if(currTime >= next) {
					//Run the task
					try {
						task.run();
					}catch(Exception e) {
						thr.getUncaughtExceptionHandler().uncaughtException(thr, e);
						if(destroyOnFail) return;
					}
					next += period;
				}
			}
		});
	}
	public void start() {
		if(state == 1) throw new IllegalStateException("The timer was already started");
		if(state == 2) throw new IllegalStateException("The timer was destroyed");
		next = System.nanoTime();
		state = 1;
		thread.start();
	}
	/**
	 * @return 0 if not started, 1 if running and 2 if destroyed
	 */
	public int getState() {
		return state;
	}
	public void destroy() {
		if(state == 0) throw new IllegalStateException("The timer was not started");
		if(state == 2) throw new IllegalStateException("The timer was destroyed");
		thread.interrupt();
		state = 2;
	}
}
