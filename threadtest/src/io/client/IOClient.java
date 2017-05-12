package io.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class IOClient {
	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 17022);
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			writer.println("Hello!");
			writer.flush();

			System.out.println("from server : " + br.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
