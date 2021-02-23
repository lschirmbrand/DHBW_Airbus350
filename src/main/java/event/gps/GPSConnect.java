package event.gps;

public class GPSConnect {
    private final String satellite;

    public GPSConnect(String satellite) {
        this.satellite = satellite;
    }

    @Override
    public String toString() {
        return "GPSConnect{" +
                "satellite='" + satellite + '\'' +
                '}';
    }
}
