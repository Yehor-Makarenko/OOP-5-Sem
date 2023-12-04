package problems;

import problems.problem1.Client;
import problems.problem1.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client();
        server.open();
        client.send("John", 32);
        server.read();
        server.close();
        server.print();
    }
}