package client;

import server.Server;

import java.util.Scanner;

public class Main extends Thread {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String inputString = scanner.nextLine();

        args = inputString.split(" ", Math.min(6, inputString.split(" ").length));

        Client client = new Client();
        client.run(inputString, args);

    }
}
