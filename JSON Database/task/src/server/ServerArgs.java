package server;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class ServerArgs {

    private ServerArgs() {

    }

    @Parameter(names = "-t")
    String commandRequest = "";
    @Parameter(names = "-i")
    int cellIndex = 0;
    @Parameter(names = "-m")
    String valueToStore = "";

    public static ServerArgs parse(String receivedMsg) {

        ServerArgs serverArgs = new ServerArgs();
        String[] argValues = receivedMsg.split(" ", Math.min(6, receivedMsg.split(" ").length));

        JCommander.newBuilder()
                .addObject(serverArgs)
                .build()
                .parse(argValues);

        return serverArgs;
    }
}