package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    ServerSocket serverSocket;
    Socket acceptSocket;
    DataOutputStream output;
    DataInputStream input;

    public static void main(String[] args) {
        Main server = new Main();
        server.run();
    }

    public void run() {
        try {
            System.out.println("Server started!");

            serverSocket = new ServerSocket(23456);
            acceptSocket = serverSocket.accept();

            output = new DataOutputStream(acceptSocket.getOutputStream());
            input = new DataInputStream(acceptSocket.getInputStream());

            String clientMessage = input.readUTF();
            System.out.println("Received: " + clientMessage);

            String sent = "A record # 23 was sent!";
            output.writeUTF(sent);
            System.out.println("Sent: " + sent);

            serverSocket.close();
            acceptSocket.close();
            output.close();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
