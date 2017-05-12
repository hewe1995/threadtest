package waitTet;

import testModel.Person;
import utils.Out;

public class T2 implements Runnable {
	private Person p;

	public T2(Person p2) {
		this.p = p2;
	}

	@Override
	public void run() {
		synchronized (p) {
			System.out.println("t2 set id");
			p.setId("t2 set id");
			Out.out("t2 notify");
			p.notify();
			Out.out("t2 end");
		}
	}

}
