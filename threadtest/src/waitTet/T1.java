package waitTet;

import testModel.Person;
import utils.Out;

public class T1 implements Runnable {
	private Person p;

	public T1(Person p) {
		this.p = p;
	}

	@Override
	public void run() {
		synchronized (p) {
			System.out.println("t1 set id");
			p.setId("t1 set id");
			try {
				Out.out("t1 wait");
				p.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			Out.out("t1 notify");
			p.setName("t1setName");
		}
	}

}
