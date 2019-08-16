package sef.module12.activity;
import java.io.*;
import java.net.Socket;
//import java.net.Socket;

public class User implements Runnable {

	private String name;

	private InputStreamReader inputStreamReader;
	private PrintWriter out;




	public User(String name, InputStream inputStreamReader, OutputStream outputStream) {
		this.inputStreamReader = new InputStreamReader(inputStreamReader);
		this.name = name;
		out = new PrintWriter(outputStream, true);
	}

	@Override
	public void run() {
		this.start();
	}

	public void start() {
		try {


			BufferedReader in = new BufferedReader(this.inputStreamReader);


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

	public void send(String str) {
		out.println(str);
	}



}
