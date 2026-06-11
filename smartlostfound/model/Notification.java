package smartlostfound.model;

public class Notification {
    private final String notifId;
    private final String userId;
    private final String message;
    private final String type;

    public Notification(String notifId, String userId, String message, String type) {
        this.notifId = notifId;
        this.userId = userId;
        this.message = message;
        this.type = type;
    }

    public String getNotifId() {
        return notifId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public String toFileString() {
        return notifId + "|" + userId + "|" + message + "|" + type;
    }
}
