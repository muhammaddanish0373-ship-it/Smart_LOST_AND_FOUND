package smartlostfound.model.service;

import smartlostfound.model.*;
import smartlostfound.model.repository.FileRepository;

import java.util.List;

public class MatchService {
    private final FileRepository repository;

    public MatchService(FileRepository repository) {
        this.repository = repository;
    }

    public void runMatching() {
        List<LostReport> lostReports = repository.getLostReports();
        List<FoundReport> foundReports = repository.getFoundReports();
        List<Match> existingMatches = repository.getAllMatches();

        for (LostReport lost : lostReports) {
            if (!"OPEN".equals(lost.getStatus())) continue;

            for (FoundReport found : foundReports) {
                if (!"OPEN".equals(found.getStatus())) continue;

                if (pairAlreadyMatched(existingMatches, lost.getReportId(), found.getReportId())) {
                    continue;
                }

                int score = calculateScore(lost, found);

                if (score >= 70) {
                    String matchId = "M" + System.nanoTime();
                    Match match = new Match(matchId, lost.getReportId(), found.getReportId(), score, "PENDING");
                    repository.saveMatch(match);
                    existingMatches.add(match);

                    Notification lostUserNotif = new Notification(
                            "N" + System.nanoTime(),
                            lost.getUserId(),
                            "Potential match found for your " + lost.getItem().getCategory()
                                    + "! Match Score: " + score + "%",
                            "MATCH_FOUND"
                    );
                    repository.saveNotification(lostUserNotif);

                    Notification foundUserNotif = new Notification(
                            "N" + (System.nanoTime() + 1),
                            found.getUserId(),
                            "The item (" + found.getItem().getCategory()
                                    + ") you found has been matched to a lost report!",
                            "MATCH_FOUND"
                    );
                    repository.saveNotification(foundUserNotif);
                }
            }
        }
    }

    private boolean pairAlreadyMatched(List<Match> matches, String lostReportId, String foundReportId) {
        for (Match match : matches) {
            if (match.getLostReportId().equals(lostReportId)
                    && match.getFoundReportId().equals(foundReportId)) {
                return true;
            }
        }
        return false;
    }

    private int calculateScore(LostReport lost, FoundReport found) {
        int score = 0;

        if (lost.getItem().getCategory().equalsIgnoreCase(found.getItem().getCategory())) {
            score += 40;
        }
        if (lost.getItem().getLocation().equalsIgnoreCase(found.getItem().getLocation())) {
            score += 30;
        }

        String lostDesc = lost.getItem().getDescription().toLowerCase();
        String foundDesc = found.getItem().getDescription().toLowerCase();
        if (foundDesc.contains(lostDesc) || lostDesc.contains(foundDesc)) {
            score += 30;
        }

        return score;
    }
}
