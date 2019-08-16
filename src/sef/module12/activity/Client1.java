package sef.module12.activity;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client1 {
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket(InetAddress.getLocalHost(), 8888);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


                while(true) {
                    System.out.print("Write here: ");
                    String word = reader.readLine();

                    out.write(word + "\n");
                    out.flush();

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Client close");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }

}