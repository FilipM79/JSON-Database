package server;

import java.io.*;
import java.net.*;

public class Server {
    protected static String receivedMsg;

    public void run() {

        System.out.println("Server started!");

        int port = 23456;
        String address = "127.0.0.1";
        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {

            try (Socket socket = server.accept();
                 DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
            {

                receivedMsg = input.readUTF();
//                System.out.println(receivedMsg);
                Args args = new Args();
                args.run(receivedMsg);


                Database database = new Database();
                database.execute();

//                String record = receivedMsg.split("# ")[1];
//                System.out.printf("Received: %s", receivedMsg);

                String outputMsg = String.format("? " + database.execute());
                output.writeUTF(outputMsg);
                System.out.printf("Sent: %s", outputMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}