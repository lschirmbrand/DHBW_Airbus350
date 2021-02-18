package event.tcas;

public class TCASScan {
    private String environment;

    @Override
    public String toString() {
        return "TCASScan{" +
                "environment='" + environment + '\'' +
                '}';
    }

    public TCASScan(String environment) {
        this.environment = environment;
    }
}
