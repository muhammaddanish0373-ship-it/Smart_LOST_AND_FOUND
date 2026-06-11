package smartlostfound.controller;

import smartlostfound.model.repository.FileRepository;
import smartlostfound.view.MatchesView;

public class MatchController {
    private final FileRepository repository;

    public MatchController(FileRepository repository) {
        this.repository = repository;
    }

    public void refreshMatches(MatchesView view) {
        view.displayMatches(repository.getAllMatches());
    }
}
