package server;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.Arrays;

public class Args {
    static String userInput;
//    public static boolean exit = false;

    @Parameter(names = "-t")
    String commandRequest = "";
    @Parameter(names = "-i")
    int cellIndex = 0;
    @Parameter(names = "-m")
    String valueToStore = "";



    public void run(String receivedMsg) {

        Args args = new Args();

//        while (!exit) {
        userInput = receivedMsg;
        System.out.println("Args user input: " + userInput);

        String[] argValues;

        if (userInput.split(" ").length < 5) {
            argValues = userInput.split(" ", userInput.split(" ").length);
        } else {
            argValues = userInput.split(" ", 6);
        }

        System.out.println(Arrays.toString(argValues));

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argValues);

    }
}