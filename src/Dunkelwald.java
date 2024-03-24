public class Dunkelwald {
    public static void main(String[] args) {
        System.out.println("Starting...");
        Server server = new Server();
        Thread.ofPlatform().name("Server").start(server);
        newClient(server);
        newClient(server);
        newClient(server);
    }

    static void newClient(Server server) {
        Client client = new Client(server);
        Thread.ofPlatform().name("Client").start(client);
        server.onClientEnter(client);
    }
}
