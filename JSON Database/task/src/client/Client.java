package client;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Client {

public void run(String[] args) {

//        String inputString = "-t set -i 1 -m \"Hello world!\"";
//        args = inputString.split(",", Math.min(6, inputString.split(" ").length));
        System.out.print("> ");
        String userInput = String.join(" ", args);
        System.out.println(userInput);
        System.out.println("Client started!");

        String serverAdress = "127.0.0.1";
        int serverPort = 23456;

        try (Socket socket = new Socket(serverAdress, serverPort);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            output.writeUTF(userInput);

            ClientArgs clientArgs = ClientArgs.parse(userInput);

            String temp = "";
            if (!clientArgs.clientCommandRequest.equals("exit")) temp = String.valueOf(clientArgs.clientCellIndex);

            System.out.print("Sent: " + clientArgs.clientCommandRequest + " " + temp + " "
                    + clientArgs.clientValueToStore);

            System.out.println();

            String receivedMsg = input.readUTF();
            System.out.printf("Received: %s", receivedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
