package client;

import java.io.*;
import java.net.Socket;

public class Main {

    Socket clientSocket;
    DataInputStream input;
    DataOutputStream output;

    public static void main(String[] args) {
        Main client = new Main();
        client.run();
    }

    public void run() {
        try {
            clientSocket = new Socket("127.0.0.1", 23456);

            output = new DataOutputStream(clientSocket.getOutputStream());
            String sent = "Give me a record # 23";
            output.writeUTF(sent);
            System.out.println("Sent: " + sent);
            input = new DataInputStream(clientSocket.getInputStream());

            String serverMessage = input.readUTF();
            System.out.println("Received: " + serverMessage);

            clientSocket.close();
            input.close();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
