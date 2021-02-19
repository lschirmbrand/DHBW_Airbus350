package event.gps;

public class GPSConnect {
    private final String satelite;

    public GPSConnect(String satelite) {
        this.satelite = satelite;
    }

    @Override
    public String toString() {
        return "GPSConnect{" +
                "satelite='" + satelite + '\'' +
                '}';
    }
}
