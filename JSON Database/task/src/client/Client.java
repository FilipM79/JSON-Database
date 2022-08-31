package client;

import com.beust.jcommander.JCommander;

import java.io.*;
import java.net.Socket;

public class Client {

        private static final String SERVER_ADDRESS = "127.0.0.1";
        private static final int SERVER_PORT = 23654;

    public static boolean hostAvailabilityCheck() {
        try (Socket s = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            return true;
        } catch (IOException ex) {
            /* ignore */
        }
        return false;
    }

    public void run(String inputString, String[] args) {

        ClientArgs clientArgs = new ClientArgs();

        JCommander.newBuilder()
                .addObject(clientArgs)
                .build()
                .parse(args);

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {

            System.out.println("Client started!");

            try (DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream())
            ) {

                output.writeUTF(inputString);

                String temp = "";
                if (!clientArgs.clientCommandRequest.equals("exit")) {
                    temp = String.valueOf(clientArgs.clientCellIndex);
                }

                System.out.println("Sent: " + clientArgs.clientCommandRequest + " " + temp + " "
                        + clientArgs.clientValueToStore);

                String receivedMsg = input.readUTF();
                System.out.printf("Received: %s", receivedMsg);
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
