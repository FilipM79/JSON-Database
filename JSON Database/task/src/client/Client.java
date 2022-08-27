package client;

import java.io.*;
import java.net.Socket;

public class Client {

    Args args = new Args();

    public void run() {
        System.out.println("Client started!");
        String SERVER_ADDRESS = "127.0.0.1";
        int SERVER_PORT = 23456;
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
        {

            String outputMsg = "Give me a record # 23";
            output.writeUTF(outputMsg);
            System.out.printf("Sent: %s", outputMsg);

            String receivedMsg = input.readUTF();
            System.out.printf("Received: %s", receivedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
