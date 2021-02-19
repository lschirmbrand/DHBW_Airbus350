package event.radar;

public class RadarScan {
    private final String environment;

    public RadarScan(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "RadarScan{" +
                "environment='" + environment + '\'' +
                '}';
    }
}
