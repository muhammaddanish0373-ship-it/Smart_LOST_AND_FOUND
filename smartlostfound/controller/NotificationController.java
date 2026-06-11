package smartlostfound.controller;

import smartlostfound.model.User;
import smartlostfound.model.repository.FileRepository;
import smartlostfound.view.NotificationsView;

import java.util.Collections;

public class NotificationController {
    private User currentUser;
    private final FileRepository repository;

    public NotificationController(FileRepository repository) {
        this.repository = repository;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void refreshNotifications(NotificationsView view) {
        if (currentUser == null) {
            view.displayNotifications(Collections.emptyList());
            return;
        }
        view.displayNotifications(repository.getNotificationsForUser(currentUser.getUserId()));
    }
}
