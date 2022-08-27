package server;

import java.io.*;
import java.net.*;

public class Server {
    private static final String address = "127.0.0.1";
    private static final int PORT = 23456;

    public void run() {
        System.out.println("Server started!");

        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(address))) {

            try (Socket socket = server.accept();
                 DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
            {

                String receivedMsg = input.readUTF();
                String record = receivedMsg.split("# ")[1];
                System.out.printf("Received: %s", receivedMsg);

                String outputMsg = String.format("A record # %s was sent!", record);
                output.writeUTF(outputMsg);
                System.out.printf("Sent: %s", outputMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}