package threadtest;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.junit.Test;

import ThreadGroup.Item1;
import ThreadGroup.Item2;
import interruptTest.InterruptThread;
import stopTest.ReadThread;
import stopTest.WriteThread;
import testModel.Person;
import testModel.Person2;
import testModel.Person2.Head;
import utils.Out;
import utils.PlainAddTread;
import waitTet.T1;
import waitTet.T2;

public class MainTest {
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	@Test
	public void rwTest() throws InterruptedException {
		Person person = new Person();
		person.setId("id");
		person.setName("name");
		WriteThread wt = new WriteThread(person);
		ReadThread rt = new ReadThread(person);

		Thread tr = new Thread(rt);
		tr.start();
		while (true) {
			Thread tw = new Thread(wt);
			tw.start();
			Thread.sleep(150);
			tw.stop();
		}
	}

	@Test
	public void interruptTest() throws InterruptedException {
		Thread t = new Thread(new InterruptThread());
		t.start();
		Thread.sleep(2000);

		t.interrupt();
		Thread.sleep(2000);
	}

	/**
	 * wait() 方法会释放锁,sleep()不会,wait()也可以设置时间
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void waitTest() throws InterruptedException {
		Person p = new Person();

		T1 t1 = new T1(p);
		T2 t2 = new T2(p);
		new Thread(t1).start();
		new Thread(t2).start();
		Thread.sleep(2000);
		Out.out("p:" + p.toString());
	}

	/**
	 * th.join(),表示将th的线程加入到主线程中,主线程等待th结束,然后在结束
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void joinTest() throws InterruptedException {
		int i = 0;
		PlainAddTread add = new PlainAddTread(i);
		Thread th = new Thread(add);
		th.start();
		th.join();
		System.out.println("main end");
		Out.out("i:" + add.getI());
	}

	/**
	 * threadGroup test
	 */
	@Test
	public void groupTest() {
		Item1 i1 = new Item1();
		Item2 i2 = new Item2();
		// 创建group
		ThreadGroup group = new ThreadGroup("testgroup");

		Thread t1 = new Thread(group, i1, "test2");
		Thread t2 = new Thread(group, i1, "test1");
		t1.start();
		t2.start();
		System.out.println("active" + group.activeCount());
		group.list();
		try {
			Thread.sleep(13000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("active" + group.activeCount());
		group.list();
	}

	@Test
	public void MapTest() {
		Person2 p = new Person2("1", "person");
		Head head = p.head(1, 2, (float) 1.1);
		Out.out(head.getQuantity());
		Out.out(p.getSumQuantity());
		head.increment(2);
		Out.out(head.getQuantity());
		Out.out(p.getSumQuantity());

		Head head1 = p.head(1, 2, (float) 1.1);

		Out.out(head1.getQuantity());
		head1.increment(100);

		Out.out(head.getQuantity());
		Out.out(p.getSumQuantity());
		Out.out(head1.getQuantity());
	}

	@Test
	public void UUIDTest() {
		String uuid = UUID.randomUUID().toString();
		//long uuidlong = UUID.fromString(uuid).timestamp();
		
		System.out.println(UUID.randomUUID().version());
	}
}
