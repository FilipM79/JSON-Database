package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database extends Args {

    String commands = "get, set, delete";
    String numbers = "0";

    public String execute() {

        String dbMessage = "";

        List<String> mainList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            mainList.add(i, "");
            numbers = numbers.concat(", " + (i + 1));
        }

        if (!commands.contains(commandRequest) || cellIndex > 1000) {
            dbMessage = "ERROR";
        } else {

            if (Objects.equals(commandRequest, "exit")) {
                dbMessage = "Bye";

            } else if (Objects.equals(commandRequest, "set")) {
                mainList.add(cellIndex, valueToStore);
                dbMessage = "OK";

            } else if (!commandRequest.contains("get")) {
                dbMessage = mainList.get(cellIndex);

            } else if (!commandRequest.contains("delete")) {
                mainList.set(cellIndex, "");
                dbMessage = "OK";
            }
        }

        return dbMessage;
    }
}
