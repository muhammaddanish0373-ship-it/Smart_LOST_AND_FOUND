package smartlostfound.model;

public abstract class Item {
    protected String itemId;
    protected String category;
    protected String description;
    protected String location;
    protected String date;

    public Item(String itemId, String category, String description, String location, String date) {
        this.itemId = itemId;
        this.category = category;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public String getItemId() {
        return itemId;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
