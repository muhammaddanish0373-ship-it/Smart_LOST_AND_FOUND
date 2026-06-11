package smartlostfound.model;

public class Match {
    private final String matchId;
    private final String lostReportId;
    private final String foundReportId;
    private final int score;
    private final String status;

    public Match(String matchId, String lostReportId, String foundReportId, int score, String status) {
        this.matchId = matchId;
        this.lostReportId = lostReportId;
        this.foundReportId = foundReportId;
        this.score = score;
        this.status = status;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getLostReportId() {
        return lostReportId;
    }

    public String getFoundReportId() {
        return foundReportId;
    }

    public int getScore() {
        return score;
    }

    public String getStatus() {
        return status;
    }

    public String toFileString() {
        return matchId + "|" + lostReportId + "|" + foundReportId + "|" + score + "|" + status;
    }
}
