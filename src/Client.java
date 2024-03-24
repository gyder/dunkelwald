import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client implements Runnable {
    Server server;
    GamePanel panel;

    Client(Server server) {
        this.server = server;
        this.panel = new GamePanel(server, this);
    }

    @Override
    public void run() {
        JFrame f = new JFrame("Dunkelwald Client");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                server.onClientExit(Client.this);
            }
        });
        f.add(panel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    void onNewBackground(Color color) {
        panel.setBackground(color);
    }

    static class GamePanel extends JPanel {

        public GamePanel(Server server, Client client) {
            setBackground(Color.BLACK);
            setFocusable(true);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Color selectedColor = JColorChooser.showDialog(
                            GamePanel.this,
                            "Choose Background Color",
                            getBackground());
                    if (selectedColor != null) {
                        server.onClientBackgroundChangeRequest(client, selectedColor);
                    }
                }
            });
        }

        public Dimension getPreferredSize() {
            return new Dimension(2560 / 2, 1440 / 2);
        }
    }
}
