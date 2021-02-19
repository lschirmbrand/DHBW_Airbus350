package event.tcas;

public class TCASScan {
    private final String environment;

    public TCASScan(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "TCASScan{" +
                "environment='" + environment + '\'' +
                '}';
    }
}
