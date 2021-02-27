public class Wheel {
    private final String id;
    private final long dateOfManufacture;
    private final Brake brake;

    public Wheel(String id, long dateOfManufacture, Brake brake) {
        this.id = id;
        this.dateOfManufacture = dateOfManufacture;
        this.brake = brake;
    }

    public String getId() {
        return id;
    }

    public long getDateOfManufacture() {
        return dateOfManufacture;
    }

    public Brake getBrake() {
        return brake;
    }
}
