package stopTest;

import testModel.Person;

public class ReadThread implements Runnable {
	private Person person;

	public ReadThread(Person person) {
		this.setPerson(person);
	}

	@Override
	public void run() {

		while (true) {
			synchronized (person) {
				if (!person.getId().equals(person.getName())) {
					System.out.println(person.toString());
				}
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
