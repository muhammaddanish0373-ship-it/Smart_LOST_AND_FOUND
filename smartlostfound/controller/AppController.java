package smartlostfound.controller;

import smartlostfound.model.User;
import smartlostfound.model.repository.FileRepository;
import smartlostfound.model.service.MatchService;
import smartlostfound.view.DashboardView;
import smartlostfound.view.LoginView;
import smartlostfound.view.MainFrame;

public class AppController {
    private final MainFrame mainFrame;
    private final FileRepository repository;
    private final MatchService matchService;

    private final LoginView loginView;
    private User currentUser;

    public AppController() {
        repository = new FileRepository();
        matchService = new MatchService(repository);
        repository.initializeFiles();

        mainFrame = new MainFrame();

        loginView = new LoginView();
        mainFrame.addScreen(loginView, "login");
        new LoginController(loginView, repository, this);

        mainFrame.showScreen("login");
    }

    public void onLoginSuccess(User user) {
        this.currentUser = user;

        mainFrame.removeScreen("dashboard");

        DashboardView dashboardView = new DashboardView(user);
        mainFrame.addScreen(dashboardView, "dashboard");
        wireDashboard(dashboardView);

        mainFrame.showScreen("dashboard");
    }

    private void wireDashboard(DashboardView dashboardView) {
        ReportController reportController = new ReportController(repository, matchService);
        reportController.setCurrentUser(currentUser);

        MatchController matchController = new MatchController(repository);
        NotificationController notificationController = new NotificationController(repository);
        notificationController.setCurrentUser(currentUser);

        dashboardView.getReportLostView().setSubmitListener(
                () -> reportController.handleLostReport(dashboardView.getReportLostView())
        );

        dashboardView.getReportFoundView().setSubmitListener(
                () -> reportController.handleFoundReport(dashboardView.getReportFoundView())
        );

        dashboardView.getMatchesView().setRefreshListener(
                () -> matchController.refreshMatches(dashboardView.getMatchesView())
        );

        dashboardView.getNotificationsView().setRefreshListener(
                () -> notificationController.refreshNotifications(dashboardView.getNotificationsView())
        );

        dashboardView.setLogoutListener(this::handleLogout);
    }

    private void handleLogout() {
        currentUser = null;
        loginView.clearFields();
        mainFrame.showScreen("login");
    }

    public void start() {
        mainFrame.setVisible(true);
    }
}
