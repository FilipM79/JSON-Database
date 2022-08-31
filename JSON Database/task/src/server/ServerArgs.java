package server;

import com.beust.jcommander.Parameter;

public class ServerArgs {

    @Parameter(names = "-t")
    String commandRequest = "";
    @Parameter(names = "-i")
    int cellIndex = 0;
    @Parameter(names = "-m")
    String valueToStore = "";

}