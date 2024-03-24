import java.io.Console;

public class Dunkelwald {
    public static void main(String[] args) {
        System.out.println("Starting...");
        Server server = new Server();
        Thread.ofPlatform().name("Server").start(server);

        Console console = System.console();
        while (true) {
            var line = console.readLine();
            if (line == null) continue;
            if (line.equals("exit")) break;
            if (line.equals("client")) newClient(server);
        }
        System.exit(0);
    }

    static void newClient(Server server) {
        Client client = new Client(server);
        Thread.ofPlatform().name("Client").start(client);
        server.onClientEnter(client);
    }
}
