package smartlostfound.controller;

import smartlostfound.model.User;
import smartlostfound.model.repository.FileRepository;
import smartlostfound.view.LoginView;

public class LoginController {
    private final LoginView view;
    private final FileRepository repository;
    private final AppController appController;

    public LoginController(LoginView view, FileRepository repository, AppController appController) {
        this.view = view;
        this.repository = repository;
        this.appController = appController;
        view.setLoginListener(this::handleLogin);
    }

    private void handleLogin() {
        String id = view.getUserId();
        String name = view.getName();
        String email = view.getEmail();

        if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
            view.showMessage("All fields are required!");
            return;
        }

        User user = repository.findUserById(id);
        if (user == null) {
            user = new User(id, name, email);
            repository.saveUser(user);
            view.showMessage("New profile registered successfully!");
        } else {
            view.showMessage("Welcome back, " + user.getName() + "!");
        }

        appController.onLoginSuccess(user);
    }
}
