package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {

    String commands = "get, set, delete";

    public String execute(String receivedCommandRequest, int receivedCellIndex, String receivedValueToStore) {
        String dbMessage = "";

        List<String> mainList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            mainList.add(i, "");
        }

        if (!commands.contains(receivedCommandRequest) || receivedCellIndex > 1000) {
            dbMessage = "ERROR";
        } else {

            if (Objects.equals(receivedCommandRequest, "exit")) {
                dbMessage = "Bye";

            } else if (Objects.equals(receivedCommandRequest, "set")) {
                mainList.add(receivedCellIndex, receivedValueToStore);
                dbMessage = "OK";

            } else if (!receivedCommandRequest.contains("get")) {
                if (Objects.equals(mainList.get(receivedCellIndex), "")) {
                    dbMessage = "ERROR";
                } else dbMessage = mainList.get(receivedCellIndex);

            } else if (!receivedCommandRequest.contains("delete")) {
                mainList.set(receivedCellIndex, "");
                dbMessage = "OK";
            }
        }

        return dbMessage;
    }
}
