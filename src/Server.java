import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

    Color background = Color.BLACK;
    List<Client> clients = new ArrayList<>();
    @Override
    public void run() {
        System.out.println("Starting server...");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    void onClientEnter(Client client) {
        clients.add(client);
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
