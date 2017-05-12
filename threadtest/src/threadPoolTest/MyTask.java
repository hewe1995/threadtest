package threadPoolTest;

import java.util.UUID;

import utils.Out;

/**
 * 
 * @author harvey
 *
 */
public class MyTask implements Runnable {
	private String name = "";

	public MyTask(String name) {
		this.name = name;
	}

	public MyTask() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		Out.out(System.currentTimeMillis() + "Thread ID=>" + Thread.currentThread().getId() + "=--->" + this.name);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "MyTask [name=" + name + "]";
	}

}
