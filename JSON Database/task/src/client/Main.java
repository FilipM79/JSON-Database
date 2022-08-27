package client;

import com.beust.jcommander.JCommander;
import java.util.Arrays;
import java.util.Scanner;

public class Main extends Args {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Args arguments = new Args();

        while (!exit) {
            String s = scanner.nextLine();

            String[] argValues;

            if (s.split(" ").length < 5) {
                argValues = s.split(" ", s.split(" ").length);
            } else {
                argValues = s.split(" ", 6);
            }

            JCommander.newBuilder()
                    .addObject(arguments)
                    .build()
                    .parse(argValues);

            System.out.println(Arrays.toString(argValues));
            arguments.run();
        }
        Client client = new Client();
        client.run();
    }

}
