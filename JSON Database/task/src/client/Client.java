package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Scanner scanner = new Scanner(System.in);

    public void run() {
        String userInput = scanner.nextLine();
        System.out.println("Client started!");

        String SERVER_ADDRESS = "127.0.0.1";
        int SERVER_PORT = 23456;


        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
        {

            output.writeUTF(userInput);
            System.out.printf("Sent: %s", userInput);
            System.out.println();
            String receivedMsg = input.readUTF();
            System.out.printf("Received: %s", receivedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
