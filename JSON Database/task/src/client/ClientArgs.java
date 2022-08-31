package client;

import com.beust.jcommander.Parameter;

public class ClientArgs {

    @Parameter(names = "-t")
    String clientCommandRequest = "";
    @Parameter(names = "-i")
    int clientCellIndex = 0;
    @Parameter(names = "-m")
    String clientValueToStore = "";

}
