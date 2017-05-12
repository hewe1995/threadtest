package io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoServer {
	private static ExecutorService tp = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket clientSocket = null;
		try {
			ss = new ServerSocket(17022);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				clientSocket = ss.accept();
				System.out.println(clientSocket.getRemoteSocketAddress() + " connect!");

				tp.execute(new HandleMsg(clientSocket));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class HandleMsg implements Runnable {
		Socket clientSocket;

		public HandleMsg(Socket socket) {
			this.clientSocket = socket;
		}

		@Override
		public void run() {

			try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true)) {
				String inputLine = null;
				long b = System.currentTimeMillis();
				while ((inputLine = br.readLine()) != null) {
					pw.println(inputLine);
				}
				long e = System.currentTimeMillis();
				System.out.println("spend:" + (e - b) + "ms");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
