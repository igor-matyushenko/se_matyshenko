package sef.module12.activity;

import java.io.*;
//import java.net.Socket;

public class User implements Runnable {

	private String name;

	private InputStreamReader stream;
	private OutputStream stream1;
	private PrintWriter out;




	public User(String name, InputStream stream, OutputStream stream1) {
		this.stream = new InputStreamReader(stream);
		this.name = name;
		out = new PrintWriter(stream1, true);
		this.stream1 = stream1;
	}

	@Override
	public void run() {
		this.start();
	}

	public void start() {
		try {


			BufferedReader in = new BufferedReader(this.stream);


			String line="";
			while ((line= in.readLine()) != null) {
				Chat.CHAT.addMessage(this, line);


				if (line.equalsIgnoreCase("exit"))
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("User disconnect");
		}
	}

	public String getName() {
		return name;
	}

}
