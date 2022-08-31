package server;

public class Main {

    public static void main(String[] args) {

        Database database = new Database();
        database.makeList();
        database.exit = false;
        Server server = new Server(database);

        System.out.println("Server started!");

        while (!database.exit) {
            args = server.run();
        }
    }
}
