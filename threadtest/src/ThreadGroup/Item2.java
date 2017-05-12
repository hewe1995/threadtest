package ThreadGroup;

public class Item2 implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
