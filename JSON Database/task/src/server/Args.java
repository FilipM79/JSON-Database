package server;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = "-t")
    public static String commandRequest = "";
    @Parameter(names = "-i")
    public static int cellIndex = 0;
    @Parameter(names = "-m")
    public static String valueToStore = "";

    public void run(String receivedMsg) {

        Args args = new Args();
        String[] argValues;

        if (receivedMsg.split(" ").length < 5) {
            argValues = receivedMsg.split(" ", receivedMsg.split(" ").length);
        } else {
            argValues = receivedMsg.split(" ", 6);
        }

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argValues);
    }
}