package event.gps;

public class GPSConnect {
    private String satelite;

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
