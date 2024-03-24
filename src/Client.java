import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Client implements Runnable {
    Server server;
    GamePanel panel;

    Client(Server server) {
        this.server = server;
        this.panel = new GamePanel(server);
    }

    @Override
    public void run() {
        JFrame f = new JFrame("Dunkelwald Client");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.add(panel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    void onNewBackground(Color color) {
        panel.setBackground(color);
    }

    static class GamePanel extends JPanel {

        public GamePanel(Server server) {
            setBackground(Color.BLACK);
            setFocusable(true);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    server.onClientBackgroundChangeRequest(null, Color.BLUE);
                }
            });
        }

        public Dimension getPreferredSize() {
            return new Dimension(2560 / 2, 1440 / 2);
        }
    }
}
