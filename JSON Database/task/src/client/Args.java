package client;

import java.util.*;
import com.beust.jcommander.Parameter;

public class Args {

    String commands = "get, set, delete";
    String numbers = "0";
    int inputNumber;
    public static boolean exit = false;
    @Parameter(names = "-t")
    String commandRequest;
    @Parameter(names = "-i")
    int cellIndex;
    @Parameter(names = "-m")
    String valueToStore = "";

    public void run() {
        Args arguments = new Args();
        System.out.println(commandRequest);
        System.out.println(cellIndex);
        System.out.println(valueToStore);
        if ("exit".equals(commandRequest)) exit = true;
        arguments.check(commandRequest,cellIndex, valueToStore);
    }

    public void check(String commandRequest, int cellIndex, String valueToStore) {

        this.commandRequest = commandRequest;
        this.cellIndex = cellIndex;
        this.valueToStore = valueToStore;

        List<String> mainList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            mainList.add(i, "");
            numbers = numbers.concat(", " + (i + 1));
        }

        if (!commands.contains(commandRequest) || cellIndex > 1000) {
            System.out.println("ERROR");
        } else {

            if (Objects.equals(commandRequest, "exit")) {
                System.out.println("Bye");

            } else if (Objects.equals(commandRequest, "set")) {
                mainList.add(cellIndex, valueToStore);
                System.out.println("OK");

            } else if (!commandRequest.contains("get")) {
                System.out.println(mainList.get(cellIndex));

            } else if (!commandRequest.contains("delete")) {
                mainList.set(cellIndex, "");
                System.out.println("OK");
            }
        }
    }
}
