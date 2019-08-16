package sef.module12.activity;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;


    public static class ClientThread extends Thread {
        Thread t;
        ClientThread() {
            t = new Thread(this);
            t.start();
        }
        @Override
        public void run() {
            String serverWord = null;
            while (true) {
                try {
                    serverWord = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(serverWord);
            }
        }
    }

    public static void main(String[] args) {
        try {
            try {
                socket = new Socket(InetAddress.getLocalHost(), 9999);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                new ClientThread();
                String line;
                while(true) {

                    line = reader.readLine();

                    out.write(line + "\n");
                    out.flush();

                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Клиент закрыт");
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }

}
