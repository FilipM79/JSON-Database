package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {

    String commands = "get, set, delete, exit";
    static boolean exit = true;
    static List<String> mainList = new ArrayList<>(1000);

    void makeList() {
        for (int i = 0; i < 1000; i++) {
            mainList.add(i, "");
        }
    }

    public String execute(String commandRequest, int cellIndex, String valueToStore) {
        String dbMessage = "";


        if (!commands.contains(commandRequest) || cellIndex > 1000) {
            dbMessage = "ERROR1";
        } else {

            switch (commandRequest) {
                case "exit":
                    dbMessage = "Bye";
                    exit = true;
                    break;

                case "set":
                    mainList.set(cellIndex, valueToStore);
                    dbMessage = "OK1";
                    break;

                case "get":
                    if (Objects.equals(mainList.get(cellIndex), "")) {
                        dbMessage = "ERROR2";
                    } else dbMessage = mainList.get(cellIndex);
                    break;

                case "delete":
                    mainList.set(cellIndex, "");
                    dbMessage = "OK2";
                    break;
            }
        }

        return dbMessage;
    }
}
