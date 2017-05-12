package threadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import utils.Out;

/**
 * 线程池测试
 * 
 * @author harvey
 *
 */
public class MainTest {
	/**
	 * 创建一个Fixed线程池.大小为5
	 */
	@Test
	public void testFixPool() {
		ExecutorService es = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; ++i) {
			MyTask myTask = new MyTask();
			es.execute(myTask);
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 计划任务测试
	 */
	@Test
	public void testScheduledPool() {
		ScheduledExecutorService es = Executors.newScheduledThreadPool(5);
		for (int i = 0; i < 10; ++i) {
			MyTask myTask = new MyTask("name" + i);
			// 会在延迟0妙后,执行,然后每i+1秒后执行一次
			// es.scheduleAtFixedRate(myTask, 0, i + 1, TimeUnit.SECONDS);
			// 开始后,延迟2秒执行,每个任务只执行一次
			// es.schedule(myTask, 2, TimeUnit.SECONDS);
			// 会在延迟0妙后,执行,然后每次执行完后延迟i+1秒后执行一次
			es.scheduleWithFixedDelay(myTask, 0, i + 1, TimeUnit.SECONDS);
		}
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 自定义拒绝策略
	 */
	@Test
	public void rejectTest() {
		MyTask task = new MyTask("test");
		ExecutorService es = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(5),
				Executors.defaultThreadFactory(), new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						// TODO Auto-generated method stub
						System.out.println(r.toString() + "is discard!!");
					}
				});
		for (int i = 0; i < 20; ++i) {
			es.submit(task);
		}
		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 自定义ThreadFactory
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testThreadFactory() throws InterruptedException {
		MyTask task = new MyTask("test");
		ExecutorService es = new ThreadPoolExecutor(5, 6, 0, TimeUnit.SECONDS, new SynchronousQueue<>(),
				new ThreadFactory() {

					@Override
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r);
						Out.out("created" + r);
						return t;
					}
				});
		for (int i = 0; i < 5; ++i) {
			es.submit(task);
		}
		Thread.sleep(4000);
	}

	@Test
	public void testListener() throws InterruptedException {
		ThreadPoolExecutor es = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()) {

			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				Out.out("准备执行:" + ((MyTask) r).toString());
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				Out.out("执行玩:" + ((MyTask) r).toString());
			}

			@Override
			protected void terminated() {
				Out.out("线程池退出");
			}

		};
		for (int i = 0; i < 10; i++) {
			MyTask task = new MyTask("test" + i);
			es.execute(task);
			Out.out("active count ->>>>>>" + es.getActiveCount());
			/* Thread.sleep(1000); */
		}
		Thread.sleep(10000);
		Out.out("final =???" + es.getActiveCount());
		Thread.sleep(10000);
		es.shutdown();
	}
}
