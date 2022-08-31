package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 23456;

    public void run(String[] args) {

        System.out.print("> ");
        String userInput = String.join(" ", args);
        System.out.println("Client started!");

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            output.writeUTF(userInput);
            ClientArgs clientArgs = ClientArgs.parse(userInput);

            String temp = "";
            if (!clientArgs.clientCommandRequest.equals("exit")) {
                temp = String.valueOf(clientArgs.clientCellIndex);
            }

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
