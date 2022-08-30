package server;

public class Main {

    public static void main(String[] args) {

        Database database = new Database();
        Server server = new Server(database);
        database.makeList();
        database.exit = false;

        System.out.println("Server started!");

        while (!database.exit) {
            server.run();
        }
    }
}
