package utils;

public class PlainAddTread implements Runnable {

	private int i = 0;

	public PlainAddTread(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		for (; i < 1000000; ++i)
			;
		Out.out("thread i" + i);
	}

	public int getI() {
		return i;
	}

}
