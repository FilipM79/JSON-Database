package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final Database database;

    public Server(Database database) {
        this.database = database;
    }

    public void run() {

        String receivedMsg;
        final int port = 23456;
        final String address = "127.0.0.1";

        try (
                ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
                Socket socket = server.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

            receivedMsg = input.readUTF();
            if (receivedMsg.equals("")) {
                System.out.println("Received message is empty");
            } else System.out.println("Received: " + receivedMsg);

            ServerArgs serverArgs = ServerArgs.parse(receivedMsg);

            database.execute(serverArgs.commandRequest, serverArgs.cellIndex, serverArgs.valueToStore);

            String outputMsg = String.format(database.execute(serverArgs.commandRequest, serverArgs.cellIndex,
                    serverArgs.valueToStore));

            output.writeUTF(outputMsg);
            System.out.printf("Sent: " +  outputMsg + "\n" + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}