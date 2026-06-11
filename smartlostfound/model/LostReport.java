package smartlostfound.model;

public class LostReport {
    private final String reportId;
    private final String userId;
    private final LostItem item;
    private final String status;

    public LostReport(String reportId, String userId, LostItem item, String status) {
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

    public LostItem getItem() {
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
