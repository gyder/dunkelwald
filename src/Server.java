import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

    public static void main(String[] args) {
        System.out.println("Starting...");
        Server server = new Server();
        Thread.ofPlatform().name("Server").start(server);
    }

    Color background = Color.BLACK;
    List<Client> clients = new ArrayList<>();
    @Override
    public void run() {
        System.out.println("Starting server...");
    }

    void onClientEnter(Client client) {
        clients.add(client);
        System.out.println(clients.size() + " clients are connected");
    }

    void onClientExit(Client client) {
        clients.remove(client);
        System.out.println(client + " has exited the game");
    }

    void fireNewBackgroundEvent() {
        for (Client client : clients) {
            client.onNewBackground(background);
        }
    }

    void onClientBackgroundChangeRequest(Client client, Color color) {
        background = color;
        fireNewBackgroundEvent();
    }
}
