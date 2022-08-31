package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(Database database) {
        this.database = database;
    }

    private final static String COMMANDS = "get, set, delete, exit";
    private final Database database;
    private boolean exit = false;
    public boolean isExit() {
        return exit;
    }

    public void run() {

        String receivedMsg;
        final int port = 23456;
        final String address = "127.0.0.1";

        System.out.println("Server run");

        try (
                ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
                Socket socket = server.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

            receivedMsg = input.readUTF();

            if (receivedMsg.equals("")) System.out.println("Received message is empty");
            else System.out.println("Received: " + receivedMsg);

            ServerArgs serverArgs = ServerArgs.parse(receivedMsg);

            String outputMsg =
                    executeCommand(serverArgs.commandRequest, serverArgs.cellIndex, serverArgs.valueToStore);

            output.writeUTF(outputMsg);
            System.out.printf("Sent: " +  outputMsg + "\n" + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String executeCommand(String commandRequest, int cellIndex, String valueToStore) {

        String outputMessage = "";

        if (!COMMANDS.contains(commandRequest) || cellIndex > 1000) {
            outputMessage = "ERROR";
        } else {

            switch (commandRequest) {
                case "exit":
                    outputMessage = "OK";
                    exit = true;
                    break;

                case "set": // pogledati
                    boolean result = database.set(cellIndex, valueToStore);
                    outputMessage = result ? "OK" : "ERROR";
                    break;

                case "get":
                    String value = database.get(cellIndex);
                    boolean isValueNull = null == value;
                    outputMessage = isValueNull ? "ERROR" : value;
                    break;

                case "delete":
                    database.delete(cellIndex);
                    outputMessage = "OK";
                    break;
            }
        }

        return outputMessage;
    }
}