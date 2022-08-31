package server;

import com.beust.jcommander.JCommander;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    String receivedMsg;
    private static final int PORT = 23654;
    private static final String ADDRESS = "127.0.0.1";
    private final Database database;

    public Server(Database database) {
        this.database = database;
    }

    public String[] run() {

        String[] argValues = new String[6];

        try (
                ServerSocket serverSocket = new ServerSocket(PORT, 50,
                        InetAddress.getByName(ADDRESS));
                Socket socket = serverSocket.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

            receivedMsg = input.readUTF();

            if (receivedMsg.equals("")) {
                System.out.println("Received message is empty");
            } else System.out.println("Received: " + receivedMsg);

            ServerArgs serverArgs = new ServerArgs();
            argValues = receivedMsg.split(" ", Math.min(6, receivedMsg.split(" ").length));

            JCommander.newBuilder()
                    .addObject(serverArgs)
                    .build()
                    .parse(argValues);

            database.execute(serverArgs.commandRequest, serverArgs.cellIndex, serverArgs.valueToStore);

            String outputMsg = String.format(database.execute(serverArgs.commandRequest, serverArgs.cellIndex,
                    serverArgs.valueToStore));

            output.writeUTF(outputMsg);
            System.out.printf("Sent: " +  outputMsg + "\n" + "\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
        return argValues;
    }
}