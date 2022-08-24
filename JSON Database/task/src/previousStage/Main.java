package previousStage;

import java.util.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command;
        String commands = "get, set, delete";
        String numbers = "0";
        String numberFromInput;
        String trimmedInput;
        int inputNumber;
        boolean exit = false;

        List<String> mainList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            mainList.add(i, "");
            numbers = numbers.concat(", " + (i + 1));
        }

        while (!exit) {
            System.out.print("> ");
            String userInput = scanner.nextLine();

            if (Objects.equals(userInput, "exit")) {
                exit = true;
            } else if (Objects.equals(userInput, "")) {
                System.out.println("ERROR");
            } else if (!userInput.contains(" ")) {
                System.out.println("ERROR");
            } else {

                command = userInput.substring(0, userInput.indexOf(" "));
                trimmedInput = userInput.substring(userInput.indexOf(" ")).trim();

                if (!commands.contains(command)) {
                    System.out.println("ERROR");
                } else {

                    if (trimmedInput.contains(" ")) {
                        numberFromInput = trimmedInput.substring(0, trimmedInput.indexOf(" ")).trim();
                    } else numberFromInput = trimmedInput;

                    if (!numbers.contains(numberFromInput)) {
                        System.out.println("ERROR");
                    } else {
                        inputNumber = Integer.parseInt(numberFromInput);

                        if (inputNumber > 100) {
                            System.out.println("ERROR");
                        } else if (Objects.equals(command, "set")) {
                            mainList.add(inputNumber, trimmedInput.substring(trimmedInput.indexOf(" ") + 1));
                            System.out.println("OK");
                        } else if (Objects.equals(command, "get")) {

                            if (Objects.equals(mainList.get(inputNumber), "")) {
                                System.out.println("ERROR");
                            } else {
                                System.out.println(mainList.get(inputNumber));
                            }

                        } else if (Objects.equals(command, "delete")) {
                            mainList.set(inputNumber, "");
                            System.out.println("OK");
                        }
                    }
                }
            }
        }
    }
}

