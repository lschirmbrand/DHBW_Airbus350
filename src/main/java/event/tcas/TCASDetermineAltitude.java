package event.tcas;

public class TCASDetermineAltitude {
    private String environment;

    public TCASDetermineAltitude(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "TCASDetermineAltitude{" +
                "environment='" + environment + '\'' +
                '}';
    }
}
