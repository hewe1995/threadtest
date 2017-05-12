package stopTest;

import testModel.Person;

public class WriteThread implements Runnable {
	private Person person;

	public WriteThread(Person person) {
		this.setPerson(person);
	}

	@Override
	public void run() {
		while (true) {
			synchronized (person) {
				String v = System.currentTimeMillis() / 1000 + "";
				person.setId(v);
				try {
					Thread.sleep(100);

				} catch (Exception e) {
				}
				person.setName(v);
			}
			Thread.yield();
		}
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
