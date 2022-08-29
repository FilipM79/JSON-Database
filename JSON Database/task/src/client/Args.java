package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.Arrays;

public class Args {

    @Parameter(names = "-t")
    public String commandRequest = "";
    @Parameter(names = "-i")
    public int cellIndex = 0;
    @Parameter(names = "-m")
    public String valueToStore = "";

    public void run(String userInput) {
        Args args = new Args();

        String[] argValues;
        System.out.println("Args user input: " + userInput);

        if (userInput.split(" ").length < 5) {
            argValues = userInput.split(" ", userInput.split(" ").length);
        } else {
            argValues = userInput.split(" ", 6);
        }

        System.out.println("Args 2 " + Arrays.toString(argValues));

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argValues);

        System.out.println("Args cr: " + commandRequest);
        System.out.println("Args ci: " + cellIndex);
        System.out.println("Args vts: " + valueToStore);
        System.out.println("argValues: " + Arrays.toString(argValues));
    }
}