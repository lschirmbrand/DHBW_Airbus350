public class Brake {
    int percentage;
    private final String id;
    private final long dateOfManufacture;

    public Brake(String id, long dateOfManufacture) {
        this.id = id;
        this.dateOfManufacture = dateOfManufacture;
        this.percentage = 0;
    }

    public String getId() {
        return id;
    }

    public long getDateOfManufacture() {
        return dateOfManufacture;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
