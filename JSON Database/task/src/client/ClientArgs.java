package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class ClientArgs {

    @Parameter(names = "-t")
    public static String clientCommandRequest = "";
    @Parameter(names = "-i")
    public static int clientCellIndex = 0;
    @Parameter(names = "-m")
    public static String clientValueToStore = "";

    public void run(String inputString) {

        ClientArgs clientArgs = new ClientArgs();
        String[] argValues;

        if (inputString.split(" ").length < 5) {
            argValues = inputString.split(" ", inputString.split(" ").length);
        } else {
            argValues = inputString.split(" ", 6);
        }

        JCommander.newBuilder()
                .addObject(clientArgs)
                .build()
                .parse(argValues);
    }
}
