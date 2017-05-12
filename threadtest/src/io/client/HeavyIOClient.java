package io.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class HeavyIOClient {
	private static ExecutorService tp = Executors.newCachedThreadPool();
	private static final int sleep_time = 1000 * 1000 * 1000;

	public static void main(String[] args) {
		EchoClient client = new EchoClient();
		for (int i = 0; i < 10; i++) {
			tp.execute(client);
		}
	}

	public static class EchoClient implements Runnable {

		@Override
		public void run() {
			try (Socket socket = new Socket("localhost", 17022);
					PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

				writer.print("H");
				LockSupport.parkNanos(sleep_time);
				writer.print("H");
				LockSupport.parkNanos(sleep_time);
				writer.print("H");
				LockSupport.parkNanos(sleep_time);
				writer.print("H");
				LockSupport.parkNanos(sleep_time);
				writer.print("H");
				LockSupport.parkNanos(sleep_time);
				writer.println();

				writer.flush();

				System.out.println("from server : " + br.readLine());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
