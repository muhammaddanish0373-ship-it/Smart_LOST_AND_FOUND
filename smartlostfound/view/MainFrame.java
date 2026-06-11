package smartlostfound.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public MainFrame() {
        setTitle("Smart Lost & Found System");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
    }

    public void addScreen(JPanel screen, String name) {
        mainPanel.add(screen, name);
    }

    public void removeScreen(String name) {
        for (Component component : mainPanel.getComponents()) {
            if (component.getName() != null && component.getName().equals(name)) {
                mainPanel.remove(component);
                break;
            }
        }
        revalidate();
        repaint();
    }

    public void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }
}
