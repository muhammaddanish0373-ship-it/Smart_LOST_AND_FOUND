package smartlostfound.model;

public class FoundReport {
    private final String reportId;
    private final String userId;
    private final FoundItem item;
    private final String status;

    public FoundReport(String reportId, String userId, FoundItem item, String status) {
        this.reportId = reportId;
        this.userId = userId;
        this.item = item;
        this.status = status;
    }

    public String getReportId() {
        return reportId;
    }

    public String getUserId() {
        return userId;
    }

    public FoundItem getItem() {
        return item;
    }

    public String getStatus() {
        return status;
    }

    public String toFileString() {
        return reportId + "|" + userId + "|" + item.getCategory() + "|"
                + item.getDescription() + "|" + item.getLocation() + "|"
                + item.getDate() + "|" + status;
    }
}
