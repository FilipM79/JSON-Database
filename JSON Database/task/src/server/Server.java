package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Server {
    protected static String receivedMsg;

    public void run() {

        System.out.println("Server started!");

        int port = 23456;
        String address = "127.0.0.1";
        String receivedCommandRequest = "";
        int receivedCellIndex = 0;
        String receivedValueToStore = "";

        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {

            try (Socket socket = server.accept();
                 DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
            {

                receivedMsg = input.readUTF();

                List<String> inputList = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    inputList.set(i, "");
                }

                for (int i = 0; i < 3; i++) {
                    if(receivedMsg.contains(" ")) {
                        inputList.set(i, receivedMsg.substring(0, receivedMsg.indexOf(" ")));
                        receivedMsg = receivedMsg.substring(receivedMsg.indexOf(" "));
                    }
                }

                if (!Objects.equals(inputList.get(0), "")) receivedCommandRequest = inputList.get(0);

                if (!Objects.equals(inputList.get(1), "")) {
                    receivedCellIndex = Integer.parseInt(inputList.get(1));
                }
                if (!Objects.equals(inputList.get(2), "")) receivedValueToStore = inputList.get(2);

                Database database = new Database();
                database.execute(receivedCommandRequest, receivedCellIndex, receivedValueToStore);

                String outputMsg = String.format(database.execute(receivedCommandRequest,
                        receivedCellIndex, receivedValueToStore));

                output.writeUTF(outputMsg);
                System.out.printf("Sent: %s", outputMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}