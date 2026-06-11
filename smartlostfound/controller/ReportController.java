package smartlostfound.controller;

import smartlostfound.model.*;
import smartlostfound.model.repository.FileRepository;
import smartlostfound.model.service.MatchService;
import smartlostfound.view.ReportFoundView;
import smartlostfound.view.ReportLostView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportController {
    private User currentUser;
    private final FileRepository repository;
    private final MatchService matchService;

    public ReportController(FileRepository repository, MatchService matchService) {
        this.repository = repository;
        this.matchService = matchService;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void handleLostReport(ReportLostView view) {
        if (currentUser == null) {
            view.showMessage("Please login first!");
            return;
        }

        String category = view.getCategory();
        String description = view.getDescription();
        String location = view.getItemLocation();

        if (category.isEmpty() || description.isEmpty() || location.isEmpty()) {
            view.showMessage("Please fill all fields!");
            return;
        }

        String itemId = "I" + System.nanoTime();
        String reportId = "LR" + System.nanoTime();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        LostItem item = new LostItem(itemId, category, description, location, date);
        LostReport report = new LostReport(reportId, currentUser.getUserId(), item, "OPEN");

        repository.saveLostReport(report);
        matchService.runMatching();

        view.showMessage("Lost report submitted!");
        view.clearFields();
    }

    public void handleFoundReport(ReportFoundView view) {
        if (currentUser == null) {
            view.showMessage("Please login first!");
            return;
        }

        String category = view.getCategory();
        String description = view.getDescription();
        String location = view.getItemLocation();

        if (category.isEmpty() || description.isEmpty() || location.isEmpty()) {
            view.showMessage("Please fill all fields!");
            return;
        }

        String itemId = "I" + System.nanoTime();
        String reportId = "FR" + System.nanoTime();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        FoundItem item = new FoundItem(itemId, category, description, location, date);
        FoundReport report = new FoundReport(reportId, currentUser.getUserId(), item, "OPEN");

        repository.saveFoundReport(report);
        matchService.runMatching();

        view.showMessage("Found report submitted!");
        view.clearFields();
    }
}
