package smartlostfound.view;

import smartlostfound.model.Notification;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NotificationsView extends JPanel {
    private final DefaultListModel<String> listModel;
    private final JButton refreshButton;

    public NotificationsView() {
        setLayout(new BorderLayout());
        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);

        refreshButton = new JButton("Refresh My Notifications");
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);
    }

    public void setRefreshListener(Runnable listener) {
        refreshButton.addActionListener(e -> listener.run());
    }

    public void displayNotifications(List<Notification> notifications) {
        listModel.clear();
        for (Notification notification : notifications) {
            listModel.addElement(notification.getMessage());
        }
    }
}
