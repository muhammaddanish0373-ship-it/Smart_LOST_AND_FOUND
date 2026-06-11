package smartlostfound.view;

import smartlostfound.model.User;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JPanel {
    private final ReportLostView reportLostView;
    private final ReportFoundView reportFoundView;
    private final MatchesView matchesView;
    private final NotificationsView notificationsView;
    private final JButton logoutButton;

    public DashboardView(User user) {
        setName("dashboard");
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("  Logged in as: " + user.getName() + " (" + user.getUserId() + ")");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(welcomeLabel, BorderLayout.NORTH);

        reportLostView = new ReportLostView();
        reportFoundView = new ReportFoundView();
        matchesView = new MatchesView();
        notificationsView = new NotificationsView();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Report Lost Item", reportLostView);
        tabbedPane.addTab("Report Found Item", reportFoundView);
        tabbedPane.addTab("View System Matches", matchesView);
        tabbedPane.addTab("My Notifications", notificationsView);
        add(tabbedPane, BorderLayout.CENTER);

        logoutButton = new JButton("Logout");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public ReportLostView getReportLostView() {
        return reportLostView;
    }

    public ReportFoundView getReportFoundView() {
        return reportFoundView;
    }

    public MatchesView getMatchesView() {
        return matchesView;
    }

    public NotificationsView getNotificationsView() {
        return notificationsView;
    }

    public void setLogoutListener(Runnable listener) {
        logoutButton.addActionListener(e -> listener.run());
    }
}
