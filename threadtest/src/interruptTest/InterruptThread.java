package interruptTest;

public class InterruptThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted");
				break;
			}
			try {
				Thread.sleep(900);// 抛出异常时会清除中断标记
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
				// 重新设置中断标记
				Thread.currentThread().interrupt();
			}
			System.out.println("thread running.....");
			Thread.yield();
		}
	}

}
