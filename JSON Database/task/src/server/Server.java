package server;

import java.io.*;
import java.net.*;

public class Server {
    static String receivedMsg;
    public void run() {

        int port = 23456;
        String address = "127.0.0.1";

        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {

            try (Socket socket = server.accept();
                 DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
            {

                receivedMsg = input.readUTF();
                if (receivedMsg.equals("")) {
                    System.out.println("Received message is empty");
                } else System.out.println("Received: " + receivedMsg);

                Args args = new Args();
                args.run(receivedMsg);

                Database database = new Database();
                database.execute(Args.commandRequest, Args.cellIndex, Args.valueToStore);

                String outputMsg = String.format(database.execute(Args.commandRequest, Args.cellIndex,
                        Args.valueToStore));

                output.writeUTF(outputMsg);
                System.out.printf("Sent: " +  outputMsg + "\n" + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}