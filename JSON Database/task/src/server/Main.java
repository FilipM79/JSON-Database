package server;

public class Main {

    public static void main(String[] args) {

        Server server = new Server();
        Database database = new Database();
        database.makeList();
        Database.exit = false;

        System.out.println("Server started!");

        while (!Database.exit) {
            server.run();
        }
    }
}
